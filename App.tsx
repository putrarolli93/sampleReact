import React from 'react';
import { NativeModules, Button, View, Text } from 'react-native';

const { AsliSmileModule } = NativeModules;

const App = () => {
  const startSmileLiveness = async () => {
    console.log('beforeStartSmile')
    try {
      const result = await AsliSmileModule.startSmileLiveness();
      console.log(result); // Output hasil ke konsol
    } catch (error) {
      console.error('Error in starting Smile Liveness:', error);
    }
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Smile Liveness Test</Text>
      <Button title="Start Smile Liveness" onPress={startSmileLiveness} />
    </View>
  );
};

export default App;