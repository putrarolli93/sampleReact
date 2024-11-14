// Di screen React Native Anda
import React from 'react';
import { View, Button } from 'react-native';
import { NativeModules } from 'react-native';

const { AsliSmile } = NativeModules;
 
const LivenessScreen = () => {
  const handleLiveness = async () => {
    try {
      const result = await AsliSmile.startLiveness('4cdfb7dd-b690-45db-8f0c-e5a8c0813d1a');
      if (result.success) {
        console.log('Liveness Check Success:', result.result);
      } else {
        console.log('Liveness Check Failed:', result.message);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Button title="Start Liveness Check" onPress={handleLiveness} />
    </View>
  );
};

export default LivenessScreen;