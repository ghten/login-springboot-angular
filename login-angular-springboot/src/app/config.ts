import { HttpHeaders } from '@angular/common/http';

export const cfg = {
  panelUrl: 'panel/',
  loginUrl: 'oauth/token',
  forgotUrl: 'forgot',
  changeurl: 'change',
  tokenName: 'token',
  registerlangs: 'register-langs',
  registercountries: 'register-countries',
  user: {
    register: 'auth/signup',
    refresh: '/refresh',
  },

  headerLogin: new HttpHeaders({
      'Content-Type':  'application/x-www-form-urlencoded', Authorization : 'Basic ' + btoa('login' + ':' + 'XXXXXXXX')
  }),
  time: '259200000',
  url_backend: 'http://localhost:8080/',
};
