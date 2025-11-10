package com.example.guide_campus_app.data;

/**
 * Esta classe serve para carregar os dados iniciais da aplicação.
 */
public class SampleData {

    /**
     * Este método verifica se a base de dados está vazia e, se estiver, adiciona os dados.
     */
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

    /**
     * Este método adiciona as localizações à base de dados.
     */
    private static void populateLocations(LocationDao dao) {
        LocationEntity[] locations = {
            createLocation("Reitoria / Serviços Académicos", "Serviços centrais", "Polo II", "Órgãos de gestão da UBI e atendimento para matrículas, certidões, propinas, etc.", "Atendimento presencial dos Serviços Académicos. Outros serviços da Reitoria funcionam em horário administrativo.", 40.273369, -7.508931, "Dias úteis: 09h00–15h00", "Horário sujeito a alterações em épocas especiais.", "ubi_reitoria"),
            createLocation("Biblioteca Central (Polo I)", "Biblioteca", "Polo I", "Biblioteca principal da UBI, com sala de leitura, salas de estudo 24h e bar.", "Bar da Biblioteca: 08h30–23h (dias úteis; fins de semana 09h–21h quando em funcionamento).", 40.277849, -7.507246, "Sala de leitura: 09h–20h (dias úteis). Salas de estudo: 24/7.", "Horários podem variar em férias ou exames.", "ubi_biblioteca_central"),
            createLocation("Biblioteca FCSH (Polo IV)", "Biblioteca", "Polo IV", "Serve os cursos de ciências sociais, gestão, economia, psicologia, etc.", "Bar FCSH: 08h–19h (dias úteis; sábados 08h–13h quando em funcionamento).", 40.288619, -7.515291, "Segunda a sexta: 09h–20h", "Confirmar horários em épocas especiais.", "ubi_biblioteca_fcsh"),
            createLocation("Biblioteca FCS (Polo III)", "Biblioteca", "Polo III", "Apoia Medicina, Ciências Biomédicas, Optometria, etc.", "Bar FCS: 08h30–18h (dias úteis).", 40.268079, -7.493597, "Segunda a sexta: 09h–20h", "Confirmar horários em épocas especiais.", "ubi_biblioteca_fcs"),
            createLocation("Faculdade de Engenharia (Polo I)", "Faculdade / Departamentos", "Polo I", "Departamentos de Eng. Eletromecânica, Civil, Aeroespacial, etc.", "Bar da Faculdade de Engenharia: 08h–18h (dias úteis).", 40.278511, -7.511747, "Aulas conforme horário do curso", "Consultar site/secretaria para detalhes.", "ubi_bloco_engenharias"),
            createLocation("Faculdade de Ciências da Saúde (Polo III)", "Faculdade", "Polo III", "Cursos de Medicina, Biomedicina, Optometria, etc.", "Serviços da faculdade em funcionamento em dias úteis, horário administrativo.", 40.267575, -7.495036, "Aulas conforme horário do curso", "Consultar site/secretaria para detalhes.", "ubi_fcs"),
            createLocation("Faculdade de Ciências Sociais e Humanas (Polo IV)", "Faculdade", "Polo IV", "Cursos de Gestão, Economia, Psicologia, Sociologia, etc.", "Serviços em dias úteis (horário administrativo).", 40.288736, -7.515106, "Aulas conforme horário de curso", "Consultar site/secretaria para detalhes.", "ubi_fcsh"),
            createLocation("Departamento de Matemática / CIC-UBI (Polo I)", "Departamento / Investigação", "Polo I", "Departamentos de Matemática e áreas afins; centro de investigação.", "Atendimento de secretariado em dias úteis (horário administrativo). O edifício é também conhecido como Sexta Fase.", 40.277864, -7.508972, "Aulas conforme horário", "Consultar site/secretaria para detalhes.", "ubi_dep_matematica"),
            createLocation("Residência Universitária Pedro Álvares Cabral (PAC)", "Residência SASUBI", "Polo IV", "Uma das principais residências SASUBI, junto ao Polo IV.", "Atendimento administrativo: dias úteis, horário de expediente.", 40.286389, -7.511944, "Acesso para residentes: 24h/24", "Confirmar horário administrativo localmente.", "ubi_residencia_pac"),
            createLocation("Centro de Apoio Médico e Desportivo (Polo II)", "Saúde / Apoio ao estudante", "Polo II", "Serviços de apoio psicológico, médico e desportivo (SASUBI).", "Marcação feita pelos canais SASUBI (online/telefone).", 40.275039, -7.507286, "Atendimento por marcação (seg-sex)", "Serviço não é de 'porta aberta'.", "ubi_centro_apoio_medico"),
            createLocation("Centro Clínico Ciências da Visão", "Saúde / Serviços", "Polo I", "Clínica de Optometria e Ciências da Visão", "Localizado no Polo I, junto ao Departamento de Informática. Disponibiliza consultas e exames.", 40.27720654581581, -7.508437210733908, "Dias úteis: 09h00–17h00", "Atendimento preferencial por marcação.", "ubi_ciencias_visao"),
            createLocation("FAL - Faculdade de Artes e Letras", "Faculdade", "Polo I", "Faculdade de Artes e Letras", "Localizada no Polo I. Abrange cursos nas áreas de artes, letras e comunicação.", 40.27746, -7.50851, "Dias úteis: 09h00–18h00", "Secretaria no piso 0.", "ubi_fal"),
            createLocation("Departamento de Informática - UBI", "Departamento / Faculdade", "Polo I", "Edifício do Departamento de Informática (DI)", "Localizado no Caminho do Biribau, Polo I. O edifício é também conhecido como Sexta Fase.", 40.27730, -7.50961, "Dias úteis: 09h-18h", "", "ubi_bloco_engenharias"),
            createLocation("Serviços de Informática - UBI", "Serviços", "Polo I", "Serviços de Informática (SI)", "Helpdesk e apoio técnico. Localizado no edifício da Faculdade de Ciências.", 40.27751758715629, -7.509767736178231, "Dias úteis: 09h-17h", "Atendimento presencial sujeito a marcação.", "ubi_dep_matematica"),
            createLocation("Faculdade de Ciências - UBI", "Faculdade / Departamentos", "Polo I", "Edifício principal da Faculdade de Ciências", "Agrega vários departamentos, incluindo Física e Química.", 40.27776411665036, -7.509246026883433, "Dias úteis: 08h-20h", "", "ubi_bloco_engenharias"),
            createLocation("Departamento de Física - UBI", "Departamento", "Polo I", "Departamento de Física", "Localizado no edifício da Faculdade de Ciências, Polo I.", 40.27769542722509, -7.508978388781471, "Dias úteis: 09h-18h", "Secretaria no piso de entrada.", "ubi_dep_matematica"),
            createLocation("Departamento de Química - UBI", "Departamento", "Polo I", "Departamento de Química", "Localizado no edifício da Faculdade de Ciências, Polo I.", 40.27747806707748, -7.50922382648791, "Dias úteis: 09h-18h", "Secretaria no piso de entrada.", "ubi_dep_matematica"),
            createLocation("Serviços Académicos", "Serviços", "Polo I", "Serviços Académicos Centrais", "Atendimento para matrículas, certidões, propinas, etc.", 40.27812, -7.50895, "Dias úteis: 09h00–15h00", "Horário sujeito a alterações.", "ubi_servicos_academicos")
        };
        dao.insertAll(locations);
    }

    /**
     * Este método adiciona os professores à base de dados.
     */
    private static void populateProfessors(ProfessorDao dao) {
        ProfessorEntity[] professors = {
                createProfessor("Ana Silva", "Departamento de Informática", "", "ana.silva@email.campus", "Ext. 1613", "Presidente do DI. Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática, Informática Web", "Bases de Dados, Aplicações sobre Bases de Dados, Sistemas de Gestão de Bases de Dados"),
                createProfessor("Bruno Costa", "Departamento de Informática", "", "bruno.costa@email.campus", "Ext. 1614", "Diretor dos cursos Cisco (CCNA). Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática, Mestrado em Eng. Informática", "Redes de Computadores, Administração de Sistemas em Rede, Nuvem, Periferia e IoT"),
                createProfessor("Carla Mendes", "Departamento de Informática", "", "carla.mendes@email.campus", "Ext. 1612", "Atendimento por marcação via e-mail ou Moodle.", "Engenharia Informática", "Programação"),
                createProfessor("David Reis", "Departamento de Matemática", "", "david.reis@email.campus", "", "Atendimento por marcação via e-mail ou Moodle.", "Gestão, Economia, Ciências Sociais", "Estatística Aplicada à Gestão, Probabilidades e Estatística, Modelos de Regressão"),
                createProfessor("Elisa Ferraz", "Departamento de Matemática", "", "elisa.ferraz@email.campus", "", "Atendimento por marcação via e-mail ou Moodle.", "Psicologia, Saúde, Gestão", "Bioestatística Aplicada, Estatística Aplicada à Psicologia I, Análise de Dados Multivariados"),
                createProfessor("Fábio Nunes", "Departamento de Matemática", "", "fabio.nunes@email.campus", "", "Atendimento por marcação via e-mail ou Moodle.", "Matemática, Engenharias", "Álgebra Linear, Álgebra I, Álgebra II"),
                createProfessor("Sofia Guedes", "Departamento de Gestão e Economia", "Gab. 3.04 (FCSH)", "sofia.guedes@email.campus", "Ext. 3859", "Atendimento por marcação via e-mail ou Moodle.", "Economia, Gestão", "Comércio e Desenvolvimento Internacional, História Económica, Fundamentos de Economia"),
                createProfessor("Hugo Martins", "Departamento de Gestão e Economia", "Gab. 3.19 (FCSH)", "hugo.martins@email.campus", "Ext. 4040", "Diretor do curso de Economia. Atendimento por marcação via e-mail ou Moodle.", "Economia, Mestrado em Economia", "Economia da Energia, Microeconomia III, Tópicos de Microeconomia, Economia Industrial"),
                createProfessor("Inês Pinto", "Departamento de Psicologia e Educação", "", "ines.pinto@email.campus", "Ext. 4819", "Presidente do DPE. Atendimento por marcação via e-mail ou Moodle.", "Mestrado em Psicologia Clínica e da Saúde", "Investigação e Análise Quantitativa, Psicologia da Sexualidade, Intervenção Psicodinâmica"),
                createProfessor("Jorge Matos", "Departamento de Ciências Médicas", "", "jorge.matos@email.campus", "", "Atendimento por marcação via e-mail, Moodle ou secretaria da FCS.", "Medicina", "Unidades curriculares do Mestrado Integrado em Medicina")
        };
        dao.insertAll(professors);
    }

    /**
     * Este método adiciona as salas à base de dados.
     */
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

    /**
     * Este método cria um objeto de localização.
     */
    private static LocationEntity createLocation(String name, String type, String campus, String shortDesc, String details, double lat, double lon, String hours, String hoursNotes, String imageName) {
        LocationEntity loc = new LocationEntity();
        loc.name = name; loc.type = type; loc.campus = campus; loc.shortDescription = shortDesc; loc.details = details;
        loc.latitude = lat; loc.longitude = lon; loc.operatingHours = hours; loc.hoursNotes = hoursNotes;
        loc.imageName = imageName;
        return loc;
    }

    /**
     * Este método cria um objeto de professor.
     */
    private static ProfessorEntity createProfessor(String name, String dept, String office, String email, String phone, String notes, String courses, String subjects) {
        ProfessorEntity prof = new ProfessorEntity();
        prof.name = name; prof.department = dept; prof.office = office; prof.email = email;
        prof.phone = phone; prof.notes = notes; 
        prof.courses = courses; prof.subjects = subjects;
        return prof;
    }

    /**
     * Este método cria um objeto de sala.
     */
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
