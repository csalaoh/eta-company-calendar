import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { ConfigurationService } from '../../shared/services/configuration.service';

@Component({
  selector: 'app-header',
  template:
  `<mat-toolbar color="primary" class="my-0">
    <a class="mr-3" mat-raised-button routerLink=".">{{'header.profile' | translate}}</a>
    <a class="mr-3" mat-raised-button routerLink=".">{{'header.calendar' | translate}}</a>
    <a class="mr-3" mat-raised-button routerLink=".">{{'header.meetings' | translate}}</a>
    <a class="mr-5"mat-raised-button routerLink=".">{{'header.reminders' | translate}}</a>
    <p class="ml-auto" (click)="onLanguageChange()">{{'header.button' | translate}}</p>
    <button mat-raised-button (click)="onLogout()" class="ml-3">{{'header.logout' | translate}}</button>
  </mat-toolbar>`
})

export class HeaderComponent {

  public language: string;

  constructor(private readonly router: Router,
              private readonly configService: ConfigurationService,
              private readonly translate: TranslateService) { }

  public onLanguageChange() {
    this.translate.use(this.language === 'en' ? 'hu' : 'en');
    this.language = this.translate.currentLang;
  }

  public onLogout() {
    this.configService.clearToken();
    this.router.navigate(['login']);
  }

}