export class Realisateur {
    id: number;
    nom: String;
    prenom: String;
    date: Date;

    public constructor(init?: Partial<Realisateur>) {
        Object.assign(this, init);
    }
}