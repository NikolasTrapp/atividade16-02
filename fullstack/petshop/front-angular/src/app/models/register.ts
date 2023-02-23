import { TreatmentType } from "./enums/treatment-type";
import { Pet } from "./pet";

export interface Register {
    id: number;
    registerDate: Date;
    treatmentType: TreatmentType;
    pet: Pet;
}
