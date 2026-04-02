import type { HybridObject } from 'react-native-nitro-modules';

export type NitroPromptKeyboardType = 'default' | 'url' | 'email';

export type NitroPromptThemeType = 'dark' | 'light' | 'system';

export interface NitroPromptOptions {
  title: string;
  placeholder?: string;
  defaultValue?: string;
  keyboardType?: NitroPromptKeyboardType;
  confirmLabel?: string;
  cancelLabel?: string;
  theme?: NitroPromptThemeType;
}

export interface NitroPrompt extends HybridObject<{
  ios: 'swift';
  android: 'kotlin';
}> {
  prompt(options: NitroPromptOptions): Promise<string | undefined>;
}
