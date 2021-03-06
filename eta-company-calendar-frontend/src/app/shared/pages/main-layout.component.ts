import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenDetails } from '../models/token-details.model';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-welcome-layout',
  styles: [ `
  .content {
    min-height: calc(100vh - 64px - 56px);
    margin-top: 84px;
    margin-bottom: 20px;
    }`],
  template: `
    <div class="d-flex flex-column">
      <app-header *ngIf="!!(tokenDetails$ | async).user_name"></app-header>
      <div class="content">
        <router-outlet></router-outlet>
      </div>
      <app-footer *ngIf="!!(tokenDetails$ | async).user_name"></app-footer>
    </div>
  `
})

export class MainLayoutComponent {
  protected tokenDetails$: Observable<TokenDetails>;

  constructor(private readonly auth: AuthService) {
    this.tokenDetails$ = this.auth.tokenDetails;
  }

}
