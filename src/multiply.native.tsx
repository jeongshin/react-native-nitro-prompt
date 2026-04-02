import { NitroModules } from 'react-native-nitro-modules';
import type { NitroPrompt } from './NitroPrompt.nitro';

const NitroPromptHybridObject =
  NitroModules.createHybridObject<NitroPrompt>('NitroPrompt');

export function multiply(a: number, b: number): number {
  return NitroPromptHybridObject.multiply(a, b);
}
