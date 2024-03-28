import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { LoginRequest } from '../../interfaces/loginRequest.interface';
import { AuthService } from '../../services/auth.service';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
    /**
     * Indique si le mot de passe est caché
     */
    public hide = true;

    /**
     * Indique si une erreur est survenue lors de la connexion
     */
    public onError = false;

    /**
     * Subscription au service d'authentification
     */
    private authSubscription: Subscription = new Subscription();

    /**
     * Formulaire de connexion
     */
    public form = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.min(3)]],
    });

    /**
     * @param authService
     * @param fb
     * @param router
     * @param sessionService
     */
    constructor(
        private authService: AuthService,
        private fb: FormBuilder,
        private router: Router,
        private sessionService: SessionService
    ) {}

    /**
     * Envoie le formulaire de connexion
     */
    public submit(): void {
        const loginRequest = this.form.value as LoginRequest;
        this.authSubscription = this.authService.login(loginRequest).subscribe(
            (response: AuthSuccess) => {
                localStorage.setItem('token', response.accessToken);
                console.log('response', response);
                this.authService.me().subscribe((user: User) => {
                    console.log('user', user);
                    this.sessionService.logIn(user);
                    this.router.navigate(['/rentals']);
                    this.authSubscription.unsubscribe();
                });
                this.router.navigate(['/rentals']);
            },
            (error) => {
                this.onError = true;
                this.authSubscription.unsubscribe();
            }
        );
    }
}
