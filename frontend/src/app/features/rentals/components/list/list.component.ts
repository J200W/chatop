import { Component } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { RentalsService } from '../../services/rentals.service';
import { Observable } from 'rxjs';
import { RentalsResponse } from '../../interfaces/api/rentalsResponse.interface';
import { Rental } from '../../interfaces/rental.interface';

@Component({
    selector: 'app-list',
    templateUrl: './list.component.html',
    styleUrls: ['./list.component.scss'],
})
export class ListComponent {
    private rentals$: Observable<RentalsResponse> = this.rentalsService.all();
    private rentals: any = [];
    private user = this.sessionService.user as User;

    constructor(
        private sessionService: SessionService,
        private rentalsService: RentalsService
    ) {}

    ngOnInit(): void {
        this.rentals$.subscribe((response) => {
            this.rentals = response;
        });
        this.user = this.sessionService.user as User;
    }

    public getRentals(): any {
        return this.rentals;
    }

    public isOwner(rental: Rental): boolean {
        return rental.owner.id === this.user.id;
    }
}
