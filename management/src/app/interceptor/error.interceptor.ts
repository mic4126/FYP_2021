import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { NoticeService } from '../services/notice.service';
import { Notice, NOTICE_TYPE_ENUM } from '../model/notice.model';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private NoticeService: NoticeService) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {


    return next.handle(request).pipe(
      catchError((err: HttpErrorResponse) => {
        if (request.method !== "GET" && !(err.error instanceof ProgressEvent)) {
          console.log(err);
          var n: Notice = {
            type: NOTICE_TYPE_ENUM.ERROR,
            message: err.error
          }
          // this.NoticeService.addNotice(n);

        }
        throw err;
      })
    )
  }
}
