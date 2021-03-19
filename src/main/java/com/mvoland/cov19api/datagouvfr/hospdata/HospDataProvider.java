package com.mvoland.cov19api.datagouvfr.hospdata;

import com.mvoland.cov19api.datagouvfr.common.DataGouvCsvBackend;
import com.mvoland.cov19api.datagouvfr.common.DataGouvParsingUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HospDataProvider {
    private final DataGouvCsvBackend<CovidHospitIncidReg> covidHospitIncidRegCsvBackend;
    private final DataGouvCsvBackend<DonneesHospitalieresClasseAgeCovid19> donneesHospitalieresClasseAgeCovid19CsvBackend;
    private final DataGouvCsvBackend<DonneesHospitalieresNouveauxCovid19> donneesHospitalieresNouveauxCovid19CsvBackend;
    private final DataGouvCsvBackend<DonneesHospitalieresCovid19> donneesHospitalieresCovid19CsvBackend;

    public HospDataProvider() {
        this.covidHospitIncidRegCsvBackend = new DataGouvCsvBackend<>(
                rowValues -> {
                    CovidHospitIncidReg data = new CovidHospitIncidReg();
                    data.setJour(DataGouvParsingUtils.parseDateOrThrow(rowValues[0]));
                    data.setNomReg(rowValues[1]);
                    data.setNumReg(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[2], null));
                    data.setIncid_rea(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[3], null));
                    return data;
                },
                "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0");

        this.donneesHospitalieresClasseAgeCovid19CsvBackend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresClasseAgeCovid19 data = new DonneesHospitalieresClasseAgeCovid19();
                    data.setReg(DataGouvParsingUtils.parseIntegerOrThrow(rowValues[0]));
                    data.setCl_age90(DataGouvParsingUtils.parseAgeGroupOrThrow(rowValues[1]));
                    data.setJour(DataGouvParsingUtils.parseDateOrThrow(rowValues[2]));
                    data.setHosp(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[3], null));
                    data.setRea(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[4], null));
                    data.setRad(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[5], null));
                    data.setDc(DataGouvParsingUtils.parseIntegerOrDefault(rowValues[6], null));
                    return data;
                },
                "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3");

        this.donneesHospitalieresNouveauxCovid19CsvBackend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresNouveauxCovid19 data = new DonneesHospitalieresNouveauxCovid19();
                    data.setDep(rowValues[0]);
                    data.setJour(rowValues[1]);
                    data.setIncid_hosp(Integer.parseInt(rowValues[2]));
                    data.setIncid_rea(Integer.parseInt((rowValues[3])));
                    data.setIncid_dc(Integer.parseInt(rowValues[4]));
                    data.setIncid_rad(Integer.parseInt((rowValues[5])));
                    return data;
                },
                "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c");

        this.donneesHospitalieresCovid19CsvBackend = new DataGouvCsvBackend<>(
                rowValues -> {
                    DonneesHospitalieresCovid19 data = new DonneesHospitalieresCovid19();
                    data.setDep(rowValues[0]);
                    data.setSexe(rowValues[1]);
                    data.setJour(rowValues[2]);
                    data.setHosp(Integer.parseInt((rowValues[3])));
                    data.setRea(Integer.parseInt((rowValues[4])));
                    data.setRad(Integer.parseInt((rowValues[5])));
                    data.setDc(Integer.parseInt((rowValues[6])));
                    return data;
                },
                "https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7");
    }

    public List<CovidHospitIncidReg> getAllCovidHospitIncidRegs() {
        return covidHospitIncidRegCsvBackend.getOrFetch();//.stream().limit(100).collect(Collectors.toList());
    }

    public List<DonneesHospitalieresClasseAgeCovid19> getAllDonneesHospitalieresClasseAgeCovid19s() {
        return donneesHospitalieresClasseAgeCovid19CsvBackend.getOrFetch();
    }

    public List<DonneesHospitalieresNouveauxCovid19> getAllDonneesHospitalieresNouveauxCovid19s() {
        return donneesHospitalieresNouveauxCovid19CsvBackend.getOrFetch();
    }

    public List<DonneesHospitalieresCovid19> getAllDonneesHospitalieresCovid19s() {
        return donneesHospitalieresCovid19CsvBackend.getOrFetch();
    }
}
