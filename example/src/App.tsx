import { useState, useCallback } from 'react';
import { Text, View, StyleSheet, Button } from 'react-native';
import { prompt } from 'react-native-nitro-prompt';

export default function App() {
  const [result, setResult] = useState<string | null>(null);

  const handlePrompt = useCallback(async () => {
    const value = await prompt({
      title: '링크 추가',
      placeholder: 'https://',
      keyboardType: 'url',
      confirmLabel: '추가',
      cancelLabel: '취소',
    });
    setResult(value);
  }, []);

  const handlePromptWithDefault = useCallback(async () => {
    const value = await prompt({
      title: '링크 수정',
      defaultValue: 'https://frontia.ai',
      placeholder: 'https://',
      keyboardType: 'url',
      confirmLabel: '수정',
      cancelLabel: '취소',
    });
    setResult(value);
  }, []);

  return (
    <View style={styles.container}>
      <Button title="링크 추가" onPress={handlePrompt} />
      <Button title="링크 수정 (기본값)" onPress={handlePromptWithDefault} />
      <Text style={styles.result}>
        {result === null ? '결과 없음 (취소됨 또는 미입력)' : `결과: ${result}`}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    gap: 16,
  },
  result: {
    fontSize: 16,
    marginTop: 16,
  },
});
