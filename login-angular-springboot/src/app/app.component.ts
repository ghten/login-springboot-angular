import { Component, Renderer2 } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AppSettings } from './app.settings';
import { Settings } from './app.settings.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  public settings: Settings;
  constructor(public appSettings: AppSettings,  private translate: TranslateService, private renderer: Renderer2) {
      this.settings = this.appSettings.settings;
      this.translate.addLangs(['en', 'es']);
      this.translate.setDefaultLang('es');

      const browserLang = this.translate.getBrowserLang();
      this.translate.use(browserLang.match(/es|en/) ? browserLang : 'es');
      this.renderer.setAttribute(document.querySelector('html'), 'lang', this.translate.currentLang);
  }
}
