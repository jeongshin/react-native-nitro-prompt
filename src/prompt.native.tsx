import { NitroModules } from 'react-native-nitro-modules';
import type { NitroPrompt, NitroPromptOptions } from './NitroPrompt.nitro';

const NitroPromptHybridObject =
  NitroModules.createHybridObject<NitroPrompt>('NitroPrompt');

export function prompt(options: NitroPromptOptions): Promise<string | null> {
  return NitroPromptHybridObject.prompt(options).then(
    (result) => result ?? null
  );
}
