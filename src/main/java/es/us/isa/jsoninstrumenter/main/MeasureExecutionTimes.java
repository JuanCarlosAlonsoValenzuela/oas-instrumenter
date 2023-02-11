package es.us.isa.jsoninstrumenter.main;

import es.us.isa.jsoninstrumenter.util.Timer;

import java.nio.file.Path;
import java.nio.file.Paths;

import static es.us.isa.jsoninstrumenter.util.FileManager.createFileIfNotExists;
import static es.us.isa.jsoninstrumenter.util.FileManager.deleteFile;


public class MeasureExecutionTimes {

    public static void main(String[] args) throws InterruptedException {

        int numberTestCases = 10000;

        String numberTestCasesFile = numberTestCases==10000 ? "10K": String.valueOf(numberTestCases);

        String[][] experiments = {
                // AmadeusHotel
                {
                        "src/test/resources/evaluationOracles/AmadeusHotel/swagger.yaml",
                        "src/test/resources/evaluationOracles/AmadeusHotel/" + numberTestCases + "/AmadeusHotel_" + numberTestCasesFile + ".csv"
                },
                // GitHub
                // createOrganizationRepository
                {
                        "src/test/resources/evaluationOracles/GitHub/createOrganizationRepository/swagger_createAnOrganizationRepository.yaml",
                        "src/test/resources/evaluationOracles/GitHub/createOrganizationRepository/" + numberTestCases + "/GitHub_createOrganizationRepository_" + numberTestCasesFile + ".csv"
                },
                // getOrganizationRepositories
                {
                        "src/test/resources/evaluationOracles/GitHub/getOrganizationRepositories/swagger_getOrganizationRepositories.yaml",
                        "src/test/resources/evaluationOracles/GitHub/getOrganizationRepositories/" + numberTestCases + "/GitHub_GetOrganizationRepositories_" + numberTestCasesFile + ".csv"
                },

                // Marvel
                // getComicById
                {
                        "src/test/resources/evaluationOracles/Marvel/getComicById/swagger_getComicById.yaml",
                        "src/test/resources/evaluationOracles/Marvel/getComicById/" + numberTestCases + "/Marvel_GetComicById_" + numberTestCasesFile + ".csv",
                        ""
                },

                // OMDb
                // byIdOrTitle
                {
                        "src/test/resources/evaluationOracles/OMDb/byIdOrTitle/swagger_byIdOrTitle.yaml",
                        "src/test/resources/evaluationOracles/OMDb/byIdOrTitle/" + numberTestCases + "/OMDb_byIdOrTitle_" + numberTestCasesFile + ".csv",
                        "N/A"
                },

                // bySearch
                {
                        "src/test/resources/evaluationOracles/OMDb/bySearch/swagger_bySearch.yaml",
                        "src/test/resources/evaluationOracles/OMDb/bySearch/" + numberTestCases + "/OMDb_bySearch_" + numberTestCasesFile + ".csv",
                        "N/A"
                },

                // Spotify
                // createPlaylist
                {
                        "src/test/resources/evaluationOracles/Spotify/createPlaylist/swagger_createPlaylist.yaml",
                        "src/test/resources/evaluationOracles/Spotify/createPlaylist/" + numberTestCases + "/Spotify_CreatePlaylist_" + numberTestCasesFile + ".csv"
                },

                // getAlbumTracks
                {
                        "src/test/resources/evaluationOracles/Spotify/getAlbumTracks/swagger_albumTracks.yaml",
                        "src/test/resources/evaluationOracles/Spotify/getAlbumTracks/" + numberTestCases + "/Spotify_GetAlbumTracks_" + numberTestCasesFile + ".csv"
                },

                // getArtistAlbums
                {
                        "src/test/resources/evaluationOracles/Spotify/getArtistAlbums/swagger_artistAlbums.yaml",
                        "src/test/resources/evaluationOracles/Spotify/getArtistAlbums/" + numberTestCases + "/Spotify_GetArtistAlbums_" + numberTestCasesFile + ".csv"
                },

                // Yelp
                {
                        "src/test/resources/evaluationOracles/Yelp/swagger.yaml",
                        "src/test/resources/evaluationOracles/Yelp/" + numberTestCases + "/Yelp_BusinessSearch_" + numberTestCasesFile + ".csv",
                        ""
                },

                // YouTube
                {
                        "src/test/resources/evaluationOracles/YouTube/openapi_getVideos.yaml",
                        "src/test/resources/evaluationOracles/YouTube/" + numberTestCases + "/YouTubeGetVideos_" + numberTestCasesFile + ".csv"
                }
        };


        int counter = 1;
        for (String[] experiment: experiments) {
            System.out.println("Experiment " + counter);
            counter = counter + 1;
            for(int i = 0; i<10; i++) {
                System.out.println("Execution " + i);
                Timer.startCounting(i);
                GenerateDeclsFile.main(experiment);
                Timer.stopCounting(i);
            }
            generateTimeReport(experiment[1]);
        }
    }

    private static void generateTimeReport(String dtracePath) {
        Path path = Paths.get(dtracePath);
        Path dir = path.getParent();
        Path fn = path.getFileSystem().getPath("time_report.csv");
        Path target = (dir == null) ? fn : dir.resolve(fn);
        String timePath = target.toString();

        deleteFile(timePath); // delete file if it exists
        createFileIfNotExists(timePath);

        try {
            Timer.exportToCSV(timePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
