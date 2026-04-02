import type { NitroPromptOptions } from './NitroPrompt.nitro';

export function prompt(_options: NitroPromptOptions): Promise<string | null> {
  throw new Error(
    "'react-native-nitro-prompt' is only supported on native platforms."
  );
}
