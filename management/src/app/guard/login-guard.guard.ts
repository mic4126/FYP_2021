import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate, CanActivateChild {
  constructor(private jwtHelper: JwtHelperService, private router: Router, private authService: AuthService) {

  }
  canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    if (this.authService.isLoggined()) {
      return true;
    }
    console.log("Cannot route to " + childRoute.pathFromRoot + " because LoginGuard check failed");
    this.router.navigate(['/login'])
    return false;
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    if (this.authService.isLoggined()) {
      return true;
    }
    console.log("Cannot route to " + route.pathFromRoot + " because LoginGuard check failed");
    this.router.navigate(['/login'])
    return false;
  }

}
