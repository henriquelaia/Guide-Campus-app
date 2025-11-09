package com.example.guide_campus_app.data;

public class SampleData {

    public static void populateIfEmpty(CampusDatabase db) {
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

    private static void populateLocations(LocationDao dao) {
        LocationEntity[] locations = {
            createLocation("Reitoria / Serviços Académicos", "Serviços centrais", "Órgãos de gestão da UBI e atendimento para matrículas, certidões, propinas, etc.", "Atendimento presencial dos Serviços Académicos. Outros serviços da Reitoria funcionam em horário administrativo.", 40.273369, -7.508931, "Dias úteis: 09h00–15h00", "Horário sujeito a alterações em épocas especiais.", "ubi_reitoria"),
            createLocation("Biblioteca Central (Polo I)", "Biblioteca", "Biblioteca principal da UBI, com sala de leitura, salas de estudo 24h e bar.", "Bar da Biblioteca: 08h30–23h (dias úteis; fins de semana 09h–21h quando em funcionamento).", 40.277849, -7.507246, "Sala de leitura: 09h–20h (dias úteis). Salas de estudo: 24/7.", "Horários podem variar em férias ou exames.", "ubi_biblioteca_central"),
            createLocation("Biblioteca FCSH (Polo IV)", "Biblioteca", "Serve os cursos de ciências sociais, gestão, economia, psicologia, etc.", "Bar FCSH: 08h–19h (dias úteis; sábados 08h–13h quando em funcionamento).", 40.288619, -7.515291, "Segunda a sexta: 09h–20h", "Confirmar horários em épocas especiais.", "ubi_biblioteca_fcsh"),
            createLocation("Biblioteca FCS (Polo III)", "Biblioteca", "Apoia Medicina, Ciências Biomédicas, Optometria, etc.", "Bar FCS: 08h30–18h (dias úteis).", 40.268079, -7.493597, "Segunda a sexta: 09h–20h", "Confirmar horários em épocas especiais.", "ubi_biblioteca_fcs"),
            createLocation("Faculdade de Engenharia (Polo I)", "Faculdade / Departamentos", "Departamentos de Eng. Eletromecânica, Civil, Aeroespacial, etc.", "Bar da Faculdade de Engenharia: 08h–18h (dias úteis).", 40.278511, -7.511747, "Aulas conforme horário do curso", "Consultar site/secretaria para detalhes.", "ubi_bloco_engenharias"),
            createLocation("Faculdade de Ciências da Saúde (Polo III)", "Faculdade", "Cursos de Medicina, Biomedicina, Optometria, etc.", "Serviços da faculdade em funcionamento em dias úteis, horário administrativo.", 40.267575, -7.495036, "Aulas conforme horário do curso", "Consultar site/secretaria para detalhes.", "ubi_fcs"),
            createLocation("Faculdade de Ciências Sociais e Humanas (Polo IV)", "Faculdade", "Cursos de Gestão, Economia, Psicologia, Sociologia, etc.", "Serviços em dias úteis (horário administrativo).", 40.288736, -7.515106, "Aulas conforme horário de curso", "Consultar site/secretaria para detalhes.", "ubi_fcsh"),
            createLocation("Departamento de Matemática / CIC-UBI (Polo I)", "Departamento / Investigação", "Departamentos de Matemática e áreas afins; centro de investigação.", "Atendimento de secretariado em dias úteis (horário administrativo).", 40.277864, -7.508972, "Aulas conforme horário", "Consultar site/secretaria para detalhes.", "ubi_dep_matematica"),
            createLocation("Residência Universitária Pedro Álvares Cabral (PAC)", "Residência SASUBI", "Uma das principais residências SASUBI, junto ao Polo IV.", "Atendimento administrativo: dias úteis, horário de expediente.", 40.286389, -7.511944, "Acesso para residentes: 24h/24", "Confirmar horário administrativo localmente.", "ubi_residencia_pac"),
            createLocation("Centro de Apoio Médico e Desportivo (Polo II)", "Saúde / Apoio ao estudante", "Serviços de apoio psicológico, médico e desportivo (SASUBI).", "Marcação feita pelos canais SASUBI (online/telefone).", 40.275039, -7.507286, "Atendimento por marcação (seg-sex)", "Serviço não é de 'porta aberta'.", "ubi_centro_apoio_medico")
        };
        dao.insertAll(locations);
    }

    private static void populateProfessors(ProfessorDao dao) {
        ProfessorEntity[] professors = {
            createProfessor("João Muranho", "Departamento de Informática", "", "jmuranho@ubi.pt", "Ext. 1613", "Presidente do DI. Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática, Informática Web", "Bases de Dados, Aplicações sobre Bases de Dados, Sistemas de Gestão de Bases de Dados"),
            createProfessor("Bruno Silva", "Departamento de Informática", "", "brunosilva@ubi.pt", "Ext. 1614", "Diretor dos cursos Cisco (CCNA). Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática, Mestrado em Eng. Informática", "Redes de Computadores, Administração de Sistemas em Rede, Nuvem, Periferia e IoT"),
            createProfessor("Pedro Almeida", "Departamento de Informática", "", "palmeida@ubi.pt", "Ext. 1612", "Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática", "Programação"),
            createProfessor("Maria Eugénia Ferrão", "Departamento de Matemática", "", "meferrao@ubi.pt", "", "Atendimento por marcação via e-mail ou Moodle.", "Gestão, Economia, Ciências Sociais", "Estatística Aplicada à Gestão, Probabilidades e Estatística, Modelos de Regressão"),
            createProfessor("Jorge Gama", "Departamento de Matemática", "", "jgama@ubi.pt", "", "Atendimento por marcação via e-mail ou Moodle.", "Psicologia, Saúde, Gestão", "Bioestatística Aplicada, Estatística Aplicada à Psicologia I, Análise de Dados Multivariados"),
            createProfessor("Deolinda Mendes", "Departamento de Matemática", "", "imendes@ubi.pt", "", "Atendimento por marcação via e-mail ou Moodle.", "Matemática, Engenharias", "Álgebra Linear, Álgebra I, Álgebra II"),
            createProfessor("Alcino Couto", "Departamento de Gestão e Economia", "Gab. 3.04 (FCSH)", "acouto@ubi.pt", "Ext. 3859", "Atendimento por marcação via e-mail ou Moodle.", "Economia, Gestão", "Comércio e Desenvolvimento Internacional, História Económica, Fundamentos de Economia"),
            createProfessor("António Marques", "Departamento de Gestão e Economia", "Gab. 3.19 (FCSH)", "amarques@ubi.pt", "Ext. 4040", "Diretor do curso de Economia. Atendimento por marcação via e-mail ou Moodle.", "Economia, Mestrado em Economia", "Economia da Energia, Microeconomia III, Tópicos de Microeconomia, Economia Industrial"),
            createProfessor("Henrique Pereira", "Departamento de Psicologia e Educação", "", "hpereira@ubi.pt", "Ext. 4819", "Presidente do DPE. Atendimento por marcação via e-mail ou Moodle.", "Mestrado em Psicologia Clínica e da Saúde", "Investigação e Análise Quantitativa, Psicologia da Sexualidade, Intervenção Psicodinâmica"),
            createProfessor("Rui Sousa", "Departamento de Ciências Médicas", "", "rmgcms@ubi.pt", "", "Atendimento por marcação via e-mail, Moodle ou secretaria da FCS.", "Medicina", "Unidades curriculares do Mestrado Integrado em Medicina")
        };
        dao.insertAll(professors);
    }

    private static void populateRooms(RoomDao dao) {
        RoomEntity[] rooms = {
            createRoom("Anfiteatro da Parada", "Anfiteatro I | Cinubiteca", "I", "Faculdade de Artes e Letras / Parada", "anfiteatro", "Não indicado", "Anfiteatro principal da Parada, usado para receções a caloiros, conferências, etc.", 0.0, 0.0),
            createRoom("2.12", "Anfiteatro 2.12", "I", "Faculdade de Artes e Letras (Parada)", "anfiteatro", "2.º piso (inferido)", "Usada para Coro da UBI, DESIGNA, UBICinema, etc. Seguir placas para 2.12.", 0.0, 0.0),
            createRoom("Pinto Peixoto", "Anfiteatro Pinto Peixoto", "I", "Zona de Ciências/Engenharia", "anfiteatro", "Não indicado", "Grande anfiteatro usado para eventos de Matemática/Física.", 0.0, 0.0),
            createRoom("Sessões Solenes", "Anfiteatro Sessões Solenes", "I", "Área central do Pólo I", "anfiteatro", "Não indicado", "Espaço cerimonial para sessões formais e conferências.", 0.0, 0.0),
            createRoom("Anfiteatro BC", "Anfiteatro Biblioteca (BC)", "I", "Biblioteca Central", "anfiteatro", "Não indicado", "Pequeno auditório (52 lugares) dentro da Biblioteca Central.", 0.0, 0.0),
            createRoom("Anfiteatro CI", "Anfiteatro Centro de Informática", "I", "Centro/Departamento de Informática", "anfiteatro", "Não indicado", "Usado para palestras de informática no edifício do DI.", 0.0, 0.0),
            createRoom("6.1", "Anfiteatro 6.1", "I", "Faculdade de Artes e Letras / bloco 6", "anfiteatro", "2.º piso", "Grande anfiteatro no bloco 6, muito usado para seminários e sessões.", 0.0, 0.0),
            createRoom("8.1", "Anfiteatro 8.1", "I", "Faculdade de Engenharia", "anfiteatro", "Piso médio (inferido)", "Muito usado pela Faculdade de Engenharia para palestras.", 0.0, 0.0),
            createRoom("6.13", "Laboratório de Apoio ao Ensino I", "I", "Departamento de Informática", "laboratorio", "2.º piso (estimado)", "Lab com 28 PCs (Windows/Linux) usado em aulas de programação.", 0.0, 0.0),
            createRoom("6.14", "Laboratório de Apoio ao Ensino II", "I", "Departamento de Informática", "laboratorio", "2.º piso (estimado)", "Lab com 25 PCs (Windows/Linux), no mesmo corredor do 6.13.", 0.0, 0.0),
            createRoom("6.19", "Laboratório de Apoio ao Ensino III", "I", "Departamento de Informática", "laboratorio", "2.º piso (estimado)", "Lab com 30 PCs (Windows/Linux), na ala de labs do DI.", 0.0, 0.0),
            createRoom("6.15", "Laboratório de Tecnologia de Computadores", "I", "Departamento de Informática", "laboratorio", "2.º piso (estimado)", "Laboratório para aulas de hardware/arquitetura de computadores.", 0.0, 0.0),
            createRoom("2.08", "Sala 2.08", "I", "Faculdade de Artes e Letras (Parada)", "sala_aula", "2.º piso (inferido)", "Sala de aulas normal, usada em eventos como o DESIGNA.", 0.0, 0.0),
            createRoom("Sala dos Conselhos", "Sala dos Conselhos - FAL", "I", "Faculdade de Artes e Letras (Parada)", "reunioes", "Não indicado", "Sala de reuniões para sessões específicas de eventos.", 0.0, 0.0),
            createRoom("11.06", "Sala 11.06", "I/II", "Área de Desporto (a confirmar)", "sala_aula", "Não indicado", "Associada à área de Desporto. Localização exata por confirmar.", 0.0, 0.0),
            createRoom("Anfiteatro SC", "Anfiteatro Serviços Centrais", "II", "Serviços Centrais da UBI (Reitoria)", "anfiteatro", "Não indicado", "Auditório oficial dos serviços centrais (60 lugares).", 0.0, 0.0),
            createRoom("Grande Auditório FCS", "Grande Auditório FCS", "III", "Faculdade de Ciências da Saúde", "anfiteatro", "Não indicado", "Auditório principal da FCS (477 lugares), para congressos e colações de grau.", 0.0, 0.0),
            createRoom("Anfiteatro A", "Anfiteatro A (Amarelo)", "III", "Faculdade de Ciências da Saúde", "anfiteatro", "Não indicado", "Anfiteatro Amarelo (111 lugares), usado para aulas de Medicina, Biomed, etc.", 0.0, 0.0),
            createRoom("Anfiteatro B", "Anfiteatro B (Azul)", "III", "Faculdade de Ciências da Saúde", "anfiteatro", "Não indicado", "Anfiteatro Azul (80 lugares).", 0.0, 0.0),
            createRoom("Anfiteatro C", "Anfiteatro C (Verde)", "III", "Faculdade de Ciências da Saúde", "anfiteatro", "Não indicado", "Anfiteatro Verde (111 lugares).", 0.0, 0.0),
            createRoom("7.20", "Anfiteatro 7.20", "IV", "Faculdade de Ciências Sociais e Humanas", "anfiteatro", "2.º piso (estimado)", "Grande anfiteatro da FCSH, usado para cerimónias e sessões de Gestão/Economia.", 0.0, 0.0),
            createRoom("7.21", "Anfiteatro Pe. Videira Pires", "IV", "Faculdade de Ciências Sociais e Humanas", "anfiteatro", "2.º piso (estimado)", "Anfiteatro 7.21, usado para aulas de psicologia, sociologia, etc.", 0.0, 0.0),
            createRoom("7.22", "Anfiteatro 7.22", "IV", "Faculdade de Ciências Sociais e Humanas", "anfiteatro", "2.º piso (estimado)", "Localizado em sequência com o 7.20 e 7.21.", 0.0, 0.0),
            createRoom("Auditório Museu", "Auditório do Museu de Lanifícios", "I", "Real Fábrica Veiga", "auditorio", "Não indicado", "Auditório do Museu de Lanifícios (60 lugares), perto do Pólo I.", 0.0, 0.0)
        };
        dao.insertAll(rooms);
    }

    private static LocationEntity createLocation(String name, String type, String shortDesc, String details, double lat, double lon, String hours, String hoursNotes, String imageName) {
        LocationEntity loc = new LocationEntity();
        loc.name = name; loc.type = type; loc.shortDescription = shortDesc; loc.details = details;
        loc.latitude = lat; loc.longitude = lon; loc.operatingHours = hours; loc.hoursNotes = hoursNotes;
        loc.imageName = imageName;
        return loc;
    }

    private static ProfessorEntity createProfessor(String name, String dept, String office, String email, String phone, String notes, String courses, String subjects) {
        ProfessorEntity prof = new ProfessorEntity();
        prof.name = name; prof.department = dept; prof.office = office; prof.email = email;
        prof.phone = phone; prof.notes = notes; 
        prof.courses = courses; prof.subjects = subjects;
        return prof;
    }

    private static RoomEntity createRoom(String code, String name, String campus, String building, String type, String floor, String description, Double latitude, Double longitude) {
        RoomEntity room = new RoomEntity();
        room.code = code;
        room.name = name;
        room.campus = campus;
        room.building = building;
        room.type = type;
        room.floor = floor;
        room.description = description;
        room.latitude = latitude;
        room.longitude = longitude;
        return room;
    }
}