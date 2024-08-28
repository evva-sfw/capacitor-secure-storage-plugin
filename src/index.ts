import { registerPlugin } from '@capacitor/core';

import type { SecureStoragePluginPlugin } from './definitions';

const SecureStoragePlugin = registerPlugin<SecureStoragePluginPlugin>('SecureStoragePlugin', {
  web: async () => await import('./web').then((m) => new m.SecureStoragePluginWeb()),
});

export * from './definitions';
export { SecureStoragePlugin };
