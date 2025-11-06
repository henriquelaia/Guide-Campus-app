package com.example.guide_campus_app.data;

public class SampleData {

    public static void populateIfEmpty(CampusDatabase db) {
        // Apenas popula se a tabela específica estiver vazia
        if (db.locationDao().count() == 0) {
            populateLocations(db.locationDao());
        }
        if (db.professorDao().count() == 0) {
            populateProfessors(db.professorDao());
        }
        if (db.roomDao().count() == 0) {
            populateRooms(db.roomDao());
        }
    }

    // --- DADOS ATUALIZADOS PARA A UBI ---

    private static void populateLocations(LocationDao dao) {
        LocationEntity[] locations = {
            createLocation("Polo I - Reitoria", "Serviços Centrais", "Edifício principal e serviços administrativos da UBI.", "O Polo I é o coração histórico da universidade, onde se localiza a Reitoria, a Biblioteca Central e as Faculdades de Ciências Sociais e Humanas e Artes e Letras.", 40.2804, -7.5086, "Seg-Sex: 09h-18h", "polo_i_reitoria"),
            createLocation("Polo II - Engenharias", "Faculdade", "Faculdade de Engenharia e Ciências Exatas.", "Conhecido como o polo das engenharias, alberga os departamentos de Informática, Eletromecânica, Civil e Aeroespacial.", 40.2858, -7.5255, "Seg-Sex: 08h-23h", "polo_ii_engenharias"),
            createLocation("Polo III - Ciências da Saúde", "Faculdade", "Faculdade de Ciências da Saúde.", "Este polo é dedicado aos cursos da área da saúde, como Medicina, Optometria e Ciências Farmacêuticas.", 40.2706, -7.5028, "Seg-Sex: 08h-20h", "polo_iii_saude"),
            createLocation("SASUBI - Cantina Central", "Serviços Sociais", "Serviços de Ação Social da UBI.", "Aqui encontras a cantina principal, o bar e os gabinetes de apoio ao estudante dos Serviços de Ação Social.", 40.2811, -7.5095, "Todos os dias: 12h-14h, 19h-21h", "sasubi_cantina"),
            createLocation("Pavilhões Desportivos", "Desporto", "Complexo desportivo da UBI.", "Inclui vários pavilhões, ginásio e campos ao ar livre para a prática de diversas modalidades desportivas.", 40.2845, -7.5245, "Todos os dias: 09h-22h", "pavilhoes_desportivos")
        };
        dao.insertAll(locations);
    }

    private static void populateProfessors(ProfessorDao dao) {
        ProfessorEntity[] professors = {
            createProfessor("Prof. Dr. Pedro Inácio", "Informática", "Gab. 6.17 (Polo II)", "pinacio@ubi.pt", "", "Especialista em Redes de Computadores.", "prof_pedro_inacio", "Engenharia Informática", "Redes de Computadores I, Segurança Informática"),
            createProfessor("Prof. Dr.ª Helena Rodrigues", "Gestão e Economia", "Gab. 3.20 (Polo I)", "hrodrigues@ubi.pt", "", "Áreas de interesse: Marketing e Estratégia.", "prof_helena_rodrigues", "Gestão, Economia", "Marketing, Gestão Estratégica"),
            createProfessor("Prof. Dr. Abel Gomes", "Informática", "Gab. 6.21 (Polo II)", "agomes@ubi.pt", "", "Especialista em Computação Gráfica.", "prof_abel_gomes", "Engenharia Informática, Design Multimédia", "Computação Gráfica, Interação Pessoa-Computador")
        };
        dao.insertAll(professors);
    }

    private static void populateRooms(RoomDao dao) {
        RoomEntity[] rooms = {
            createRoom("Anfiteatro 6.01", "Polo II - Engenharias", "0", "Anfiteatro principal do Bloco 6.", 40.2858, -7.5255, "anfiteatro_601"),
            createRoom("Sala 3.01", "Polo I - Reitoria", "3", "Sala de aula no terceiro piso do polo principal.", 40.2804, -7.5086, "sala_301"),
            createRoom("Laboratório 1.5", "Polo III - Ciências da Saúde", "1", "Laboratório de análises.", 40.2706, -7.5028, "laboratorio_105")
        };
        dao.insertAll(rooms);
    }

    // --- Métodos de Criação ---

    private static LocationEntity createLocation(String name, String type, String shortDesc, String details, double lat, double lon, String hours, String imageName) {
        LocationEntity loc = new LocationEntity();
        loc.name = name; loc.type = type; loc.shortDescription = shortDesc; loc.details = details;
        loc.latitude = lat; loc.longitude = lon; loc.operatingHours = hours; loc.imageName = imageName;
        return loc;
    }

    private static ProfessorEntity createProfessor(String name, String dept, String office, String email, String phone, String notes, String imageName, String courses, String subjects) {
        ProfessorEntity prof = new ProfessorEntity();
        prof.name = name; prof.department = dept; prof.office = office; prof.email = email;
        prof.phone = phone; prof.notes = notes; prof.imageName = imageName; 
        prof.courses = courses; prof.subjects = subjects;
        return prof;
    }

    private static RoomEntity createRoom(String code, String building, String floor, String desc, Double lat, Double lon, String imageName) {
        RoomEntity room = new RoomEntity();
        room.code = code; room.building = building; room.floor = floor; room.description = desc;
        room.latitude = lat; room.longitude = lon; room.imageName = imageName;
        return room;
    }
}