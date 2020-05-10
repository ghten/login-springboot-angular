import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { AuthService } from '../services/auth.service';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authservice: AuthService, private router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      catchError(e => {
        if (e.status === 401) {

          if (this.authservice.isAuthenticated()) {
            this.authservice.logout();
          }
          this.router.navigate(['/login']);
        }

        if (e.status === 403) {
          swal.fire('Access denied', ` you don't have access to this resource!`, 'warning');
          this.authservice.logout();
          this.router.navigate(['/login']);
        }

        return throwError(e);
      })
    );
  }
}
