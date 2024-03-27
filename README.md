# Bath procedures scheduling

This project can **generate** a **timetable** for patients in a **bath complexum**. The **doctors** and **nurses** can registrate in the system and can **prescribe** the **treatments** for their patients. 
After they selected the treatments the system will **automaticaly generate** a timetable considering the lenght and the numbers of the treatments, the available places. 
If the time is not acceptable for the patients we can change it manually. 
After the timetable is looking good we can print it and give it to the patient. The system save the used treatments and we can see a simlpy statistics about the procedures and about the patients treatments. 
The doctors have more access to the system and they can record the patient's details and complaints with comments.

## Technological Stack

- **Application Type:** Web Application
- **Language:** Java 1.8
- **Framework:** SpringBoot 2.2.0
- **Database Management:** PostgreSQL, H2
- **User Interface:** HTML, CSS, Bootstrap, JavaScript
- **ORM:** Spring Data JPA
- **Template Engine:** Thymeleaf
- **Design Pattern:** MVC

### Prescribing procedures for patients
![Procedúrák_felírása](https://github.com/sznikolas/bath-procedures-scheduling/assets/48528872/7bff2bbf-5fcd-4795-90b0-0527aff21056)

### Description of procedures
![Procedúrák_leírása](https://github.com/sznikolas/bath-procedures-scheduling/assets/48528872/8d1ba59f-72c9-4624-b3f9-a37be09939d1)

### Generated timetable for the patient
![Kezelési_engedély](https://github.com/sznikolas/bath-procedures-scheduling/assets/48528872/a7028ef8-2522-4683-b575-90784ee103e9)

### Map of the bath complex
![Térkép](https://github.com/sznikolas/bath-procedures-scheduling/assets/48528872/2564e282-50b7-4ab6-b2d0-da04e91e1d4d)

# Additional information about the project

The source code is outdated and needs updating (+ refactoring), and we also need to add the appointment times for the treatments. Hungarian language in many places in the code.
