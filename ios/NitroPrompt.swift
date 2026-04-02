import UIKit
import NitroModules

class NitroPrompt: HybridNitroPromptSpec {
  func prompt(options: NitroPromptOptions) throws -> Promise<String?> {
    return .async {
      return try await withCheckedThrowingContinuation { continuation in
        DispatchQueue.main.async {
          guard let windowScene = UIApplication.shared.connectedScenes
            .compactMap({ $0 as? UIWindowScene })
            .first(where: { $0.activationState == .foregroundActive }),
            let rootVC = windowScene.windows.first(where: { $0.isKeyWindow })?.rootViewController
          else {
            continuation.resume(returning: nil)
            return
          }

          var topVC = rootVC
          while let presented = topVC.presentedViewController {
            topVC = presented
          }

          let alert = UIAlertController(
            title: options.title,
            message: nil,
            preferredStyle: .alert
          )

          if let theme = options.theme {
            switch theme {
            case .light:
              alert.overrideUserInterfaceStyle = .light
            case .dark:
              alert.overrideUserInterfaceStyle = .dark
            case .system:
              break
            }
          }

          alert.addTextField { textField in
            textField.placeholder = options.placeholder
            textField.text = options.defaultValue

            if let keyboardType = options.keyboardType {
              switch keyboardType {
              case .default:
                textField.keyboardType = .default
              case .url:
                textField.keyboardType = .URL
              case .email:
                textField.keyboardType = .emailAddress
              }
            }
          }

          let cancelTitle = options.cancelLabel ?? "Cancel"
          alert.addAction(UIAlertAction(title: cancelTitle, style: .cancel) { _ in
            continuation.resume(returning: nil)
          })

          let confirmTitle = options.confirmLabel ?? "OK"
          alert.addAction(UIAlertAction(title: confirmTitle, style: .default) { _ in
            let text = alert.textFields?.first?.text
            continuation.resume(returning: text)
          })

          topVC.present(alert, animated: true)
        }
      }
    }
  }
}
