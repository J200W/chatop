export interface Rental {
	id: number,
	name: string,
	surface: number,
	price: number,
	picture: string,
	description: string,
	owner: {
		id: number,
		name: string,
		email: string
	},
	created_at: Date,
	updated_at: Date
}