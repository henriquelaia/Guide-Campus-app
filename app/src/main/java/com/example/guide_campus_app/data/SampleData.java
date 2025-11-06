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
            createLocation("Reitoria da UBI", "Serviços Centrais", "Sede administrativa e gabinetes da Reitoria.", "O edifício do antigo convento de Santo António acolhe a Reitoria, a Tesouraria, os gabinetes de apoio ao estudante e a Biblioteca Central.", 40.279976, -7.505955, "Seg-Sex: 09h00-18h00", "ubi_reitoria"),
            createLocation("Biblioteca Central da UBI", "Biblioteca", "Espaço de estudo e consulta bibliográfica.", "A Biblioteca Central disponibiliza salas de estudo abertas até tarde, serviço de empréstimo interbibliotecas e acesso ao repositório científico da universidade.", 40.280861, -7.506805, "Seg-Sex: 09h00-21h00; Sáb: 09h00-13h00", "ubi_biblioteca_central"),
            createLocation("Faculdade de Engenharia (Polo II)", "Faculdade", "Departamentos de Engenharia da UBI.", "O complexo do Pólo II alberga os departamentos de Informática, Eletromecânica, Aeroespacial, Civil e o laboratório de Aerodinâmica.", 40.282974, -7.505248, "Seg-Sex: 08h30-22h00", "ubi_polo_ii"),
            createLocation("Departamento de Informática - Bloco 6", "Departamento", "Departamento de Ciências da Computação.", "O bloco 6 reúne salas de aula, anfiteatros, laboratórios de software e centros de investigação na área de informática.", 40.283134, -7.504716, "Seg-Sex: 08h30-21h00", "ubi_bloco6"),
            createLocation("Faculdade de Ciências da Saúde (Polo III)", "Faculdade", "Cursos e laboratórios da área da saúde.", "Instalações modernas com anfiteatros clínicos, gabinetes de simulação e o centro UBImedical para transferência de tecnologia em saúde.", 40.271058, -7.501900, "Seg-Sex: 08h00-20h00", "ubi_polo_iii"),
            createLocation("UBImedical", "Incubadora", "Incubadora de empresas de saúde e tecnologias médicas.", "Espaço dedicado à investigação aplicada, startups e projetos de empreendedorismo ligados à biotecnologia e dispositivos médicos.", 40.275820, -7.497640, "Seg-Sex: 09h00-18h00", "ubi_ubimedical"),
            createLocation("Museu de Lanifícios - Real Fábrica de Panos", "Museu", "Património industrial ligado à história da Covilhã.", "O museu apresenta exposições permanentes sobre a transformação da lã e acolhe regularmente eventos culturais organizados pela UBI.", 40.281661, -7.505079, "Ter-Dom: 10h00-18h00", "ubi_museu_lanificios"),
            createLocation("Residência Universitária de Santo António", "Residência", "Alojamento para estudantes no coração do Polo I.", "Residência histórica com quartos individuais e duplos, salas de convívio e apoio social para estudantes deslocados.", 40.278021, -7.505896, "Receção: 24h", "ubi_residencia_santo_antonio"),
            createLocation("Residência Universitária do Polo II", "Residência", "Alojamento estudantil junto à Faculdade de Engenharia.", "Edifício moderno com quartos equipados, lavandaria e zonas comuns a poucos metros dos laboratórios de engenharia.", 40.284430, -7.507030, "Receção: 08h00-22h00", "ubi_residencia_polo2"),
            createLocation("Complexo Desportivo da UBI", "Desporto", "Pavilhões e ginásios para prática desportiva.", "O complexo integra campos interiores polivalentes, salas de treino, ginásio cardiovascular e apoio médico-desportivo aos estudantes.", 40.283732, -7.523980, "Seg-Sex: 08h00-22h00; Sáb: 09h00-19h00", "ubi_complexo_desportivo"),
            createLocation("SASUBI - Cantina Central", "Serviços Sociais", "Refeitório e serviços de ação social.", "A cantina central serve refeições económicas, snacks e dispõe de atendimento presencial para bolsas, alojamento e apoio psicológico.", 40.281132, -7.509420, "Seg-Dom: 11h45-14h30; 19h00-21h00", "ubi_sasubi")
        };
        dao.insertAll(locations);
    }

    private static void populateProfessors(ProfessorDao dao) {
        ProfessorEntity[] professors = {
            createProfessor("Prof. Doutor Pedro Inácio", "Engenharia Informática", "Gab. 6.17 (Bloco 6, Polo II)", "pinacio@ubi.pt", "+351 275 242 081", "Investigador no LabCom IF com foco em segurança de redes e cibersegurança.", "prof_pedro_inacio", "Engenharia Informática", "Redes de Computadores, Segurança Informática"),
            createProfessor("Prof. Doutor Abel Gomes", "Engenharia Informática", "Gab. 6.21 (Bloco 6, Polo II)", "agomes@ubi.pt", "+351 275 242 081", "Coordena o grupo de investigação em Computação Gráfica e Visualização.", "prof_abel_gomes", "Engenharia Informática, Design Multimédia", "Computação Gráfica, Interação Pessoa-Computador"),
            createProfessor("Prof. Doutor João Canavilhas", "Comunicação e Artes", "Gab. 2.15 (Polo I)", "joao.canavilhas@ubi.pt", "+351 275 319 700", "Especialista em jornalismo digital e vice-reitor para o ensino.", "prof_joao_canavilhas", "Ciências da Comunicação", "Ciberjornalismo, Comunicação Política"),
            createProfessor("Prof. Doutora Ana Paula Duarte", "Ciências da Saúde", "Gab. 1.12 (Polo III)", "apduarte@ubi.pt", "+351 275 329 002", "Investigadora no CICS-UBI nas áreas de farmacologia clínica e toxicologia.", "prof_ana_paula_duarte", "Ciências Farmacêuticas", "Farmacologia Clínica, Toxicologia"),
            createProfessor("Prof. Doutor Miguel Castelo-Branco", "Aeronáutica e Astronáutica", "Gab. 8.14 (Polo II)", "mcastelobranco@ubi.pt", "+351 275 242 081", "Responsável pela licenciatura em Engenharia Aeroespacial.", "prof_miguel_castelo_branco", "Engenharia Aeroespacial", "Mecânica dos Voos, Projeto Aeroespacial"),
            createProfessor("Prof. Doutor Carlos Alberto Pereira", "Engenharia Eletromecânica", "Gab. 9.04 (Polo II)", "capereira@ubi.pt", "+351 275 242 081", "Coordena projetos de eficiência energética no centro C-MAST.", "prof_carlos_pereira", "Engenharia Eletromecânica", "Máquinas Elétricas, Sistemas de Energia"),
            createProfessor("Prof. Doutora Helena Rodrigues", "Gestão e Economia", "Gab. 3.20 (Polo I)", "hrodrigues@ubi.pt", "+351 275 319 700", "Investigadora do NECE em marketing estratégico e comportamento do consumidor.", "prof_helena_rodrigues", "Gestão, Economia", "Marketing, Gestão Estratégica"),
            createProfessor("Prof. Doutora Fátima Matos", "Ciências Sociais", "Gab. 1.06 (Polo I)", "fmatos@ubi.pt", "+351 275 319 600", "Docente de Sociologia com interesse em políticas públicas e demografia.", "prof_fatima_matos", "Sociologia", "Métodos de Investigação Social, Sociologia Urbana")
        };
        dao.insertAll(professors);
    }

    private static void populateRooms(RoomDao dao) {
        RoomEntity[] rooms = {
            createRoom("Anfiteatro 6.01", "Bloco 6 - Polo II", "0", "Anfiteatro principal utilizado para aulas de Engenharia Informática e eventos científicos.", 40.283210, -7.504930, "anfiteatro_601"),
            createRoom("Laboratório de Redes 6.23", "Bloco 6 - Polo II", "2", "Laboratório equipado com routers Cisco e infraestrutura para aulas práticas de redes.", 40.283134, -7.504716, "laboratorio_redes_623"),
            createRoom("Sala Sénior 2.15", "Edifício da Reitoria", "2", "Sala com equipamentos multimédia usada por mestrados da FCSH.", 40.279976, -7.505955, "sala_senior_215"),
            createRoom("Anfiteatro Verde", "Faculdade de Ciências da Saúde", "0", "Auditório principal com capacidade para 240 lugares e equipamentos de telemedicina.", 40.271058, -7.501900, "anfiteatro_verde"),
            createRoom("Laboratório UBImedical Sala Limpa", "UBImedical", "1", "Infraestrutura certificada para prototipagem de dispositivos médicos.", 40.275820, -7.497640, "ubimedical_sala_limpa"),
            createRoom("Biblioteca - Sala de Estudo 24h", "Biblioteca Central", "0", "Sala silenciosa com acesso 24 horas para estudantes durante a época de exames.", 40.280861, -7.506805, "biblioteca_sala24h")
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