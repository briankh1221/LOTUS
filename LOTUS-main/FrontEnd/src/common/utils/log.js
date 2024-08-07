// Log Debug 설정
const LOG_DEBUG = true;
const LOG_INFO = true;
const LOG_ERROR = true;
const SYSTEM_DEBUG = true;

const log = {};
const $subMsgTlt = {
  gfn: '[GLOBAL]',
  jwt: '[JWT]',
  util: '[UTIL]',
  axios: '[AXIOS]',
};
// debug
log.debug = function (...arg) {
  if (!LOG_DEBUG) return;
  console.log('', ...arg);
};
// info
log.info = function (...arg) {
  if (!LOG_INFO) return;
  console.info('', ...arg);
};
// error
log.error = function (...arg) {
  if (!LOG_ERROR) return;
  console.error('[ERROR]', ...arg);
};
// system
log.system = function (...arg) {
  if (!SYSTEM_DEBUG) return;
  console.log('[SYSTEM]', ...arg);
};

export { log };
