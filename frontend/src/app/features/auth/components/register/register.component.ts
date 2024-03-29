import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from 'src/app/services/session.service';
import { AuthService } from '../../services/auth.service';
import { RegisterRequest } from '../../interfaces/registerRequest.interface';
import { AuthSuccess } from '../../interfaces/authSuccess.interface';
import { User } from 'src/app/interfaces/user.interface';
import { pipe } from 'rxjs';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
    /**
     * Indique si une erreur est survenue lors de l'inscription
     * @type {boolean}
     * @memberof RegisterComponent
     * @default false
     * @public
     *
     */
    public onError: boolean = false;

    /**
     * Formulaire d'inscription
     * @type {FormGroup}
     * @memberof RegisterComponent
     * @public
     *
     */

    public form: FormGroup = this.fb.group({
        email: ['', [Validators.required, Validators.email]],
        name: ['', [Validators.required, Validators.min(3)]],
        password: ['', [Validators.required, Validators.min(3)]],
    });

    constructor(
        private authService: AuthService,
        private fb: FormBuilder,
        private router: Router,
        private sessionService: SessionService
    ) {}

    /**
     * Envoie le formulaire d'inscription
     * @memberof RegisterComponent
     * @public
     *
     */

    public submit(): void {
        const registerRequest = this.form.value as RegisterRequest;
        console.log('registerRequest', registerRequest);
        this.authService.register(registerRequest).subscribe(
            pipe(
                (response: AuthSuccess) => {
                    localStorage.setItem('token', response.token);
                    console.log("localStorage.getItem('token')", localStorage.getItem('token'));
                    this.authService.me().subscribe((user: User) => {
                        this.sessionService.logIn(user);
                        this.router.navigate(['/rentals']);
                    });
                },
                (error) => (this.onError = true)
            )
        );
    }
}
