# react-native-nitro-prompt

Native prompt dialog for React Native, powered by [Nitro Modules](https://nitro.margelo.com/).

- iOS: `UIAlertController` with text field
- Android: `AlertDialog` with `EditText`

## Installation

```sh
npm install react-native-nitro-prompt react-native-nitro-modules
```

> `react-native-nitro-modules` is a required peer dependency.

### iOS

```sh
cd ios && pod install
```

## Usage

```ts
import { prompt } from 'react-native-nitro-prompt';

const url = await prompt({
  title: 'Add Link',
  placeholder: 'https://',
  keyboardType: 'url',
  confirmLabel: 'Add',
  cancelLabel: 'Cancel',
});

if (url) {
  // user entered a value
}
```

### Edit existing value

```ts
const url = await prompt({
  title: 'Edit Link',
  defaultValue: 'https://example.com',
  placeholder: 'https://',
  keyboardType: 'url',
  confirmLabel: 'Save',
  cancelLabel: 'Cancel',
});
```

## API

### `prompt(options): Promise<string | null>`

Shows a native prompt dialog and returns the entered string, or `null` if cancelled.

### `NitroPromptOptions`

| Property | Type | Required | Default | Description |
|---|---|---|---|---|
| `title` | `string` | Yes | - | Dialog title |
| `placeholder` | `string` | No | - | Input placeholder text |
| `defaultValue` | `string` | No | - | Pre-filled input value |
| `keyboardType` | `'default' \| 'url' \| 'email'` | No | `'default'` | Keyboard type |
| `confirmLabel` | `string` | No | `'OK'` | Confirm button label |
| `cancelLabel` | `string` | No | `'Cancel'` | Cancel button label |
| `theme` | `'dark' \| 'light' \| 'system'` | No | `'system'` | Dialog theme |

## License

MIT
