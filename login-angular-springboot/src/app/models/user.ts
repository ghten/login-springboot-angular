export class User {
    public id: number;
    public name: string;
    public surname: string;
    public email: string;
    public password: string;
    public companies_id: string;
    public enabled: boolean;
    public updated_at: string;
    public created_at: string;
    public role: string[] = [];
}

