import { CoatType } from "./enums/coat-type";
import { Owner } from "./owner";

export interface Pet {
    id: number;
    name: string;
    race: string;
    heigth: number;
    weight: number;
    coatType: CoatType;
    owner: Owner;
}
