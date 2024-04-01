export interface User {
	id: number,
	username: string,
    name: string,
	email: string,
	created_at: Date,
	updated_at: Date,
    roles: string[],
}
