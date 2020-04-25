package models;

import java.util.Random;

public enum Specialization {
    AdultIntensivist,
    Allergy,
    Anesthesia,
    BariatricSurgery,
    BurnOrTrauma,
    CardiacCatheterization,
    Cardiology,
    CardiovascularSurgery,
    ColorectalSurgery,
    Dermatology,
    Electrophysiology,
    EmergencyMedicine,
    Endocrinology,
    FamilyPractice,
    Gastroenterology,
    GeneralSurgery,
    Geriatrics,
    GynecologicOncology,
    Hematology_Oncology,
    Hepatobiliary,
    Hospitalist,
    InfectiousDisease,
    InternalMedicine,
    InterventionalRadiology,
    MedicalGenetics,
    Neonatology,
    Nephrology,
    Neuroradiology,
    Neurology,
    Neurosurgery,
    NuclearMedicine,
    ObstetricsAndGynecology,
    OccupationalMedicine,
    Ophthalmology,
    OralSurgery,
    Orthopedics,
    Otolaryngology,
    HeadAndNeckSurgery,
    PainManagement,
    PalliativeCare,
    Pathology_SurgicalAndAnatomic,
    PediatricIntensivist,
    Pediatrics,
    PediatricSurgery,
    PhysicalMedicine;

    public static Specialization getRandom() {
        Random random = new Random();

        return values()[random.nextInt(values().length)];
    }
}
