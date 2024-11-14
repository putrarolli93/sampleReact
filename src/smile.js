import { NativeModules, Platform } from 'react-native';

const { AsliSmile } = NativeModules;

if (!AsliSmile) {
  throw new Error(
    `AsliSmile module is not available. Platform: ${Platform.OS}`
  );
}

const AsliSmileModule = {
  initialize(apiKey) {
    if (typeof apiKey !== 'string') {
      throw new Error('API Key must be a string');
    }
    return AsliSmile.initialize(apiKey);
  },

  startLiveness() {
    return AsliSmile.startLiveness();
  }
};

module.exports = AsliSmileModule;