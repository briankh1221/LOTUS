import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from '@/router';

import 'bootstrap/dist/css/bootstrap.css'; // 부트스트랩 CSS
import 'bootstrap/dist/js/bootstrap.bundle.min.js'; // 부트스트랩 JS
import 'bootstrap-icons/font/bootstrap-icons.css'; // 부트스트랩 icons

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount('#app');
