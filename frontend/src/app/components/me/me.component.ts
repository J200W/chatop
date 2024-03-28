import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';

@Component({
    selector: 'app-me',
    templateUrl: './me.component.html',
    styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
    public user: User | undefined;
    private authSubscription: Subscription = new Subscription();

    constructor(private authService: AuthService) {}

    public ngOnInit(): void {
        this.authSubscription = this.authService.me().subscribe((user: User) => {
            this.user = user;
            console.log(user);
            this.authSubscription.unsubscribe();
        });
    }

    public back() {
        window.history.back();
    }
}
