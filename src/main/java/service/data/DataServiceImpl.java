package service.data;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;

import model.District;
import model.ElectionData;
import model.GeoData;
import model.State;
import org.springframework.beans.factory.annotation.Autowired;
import persistence.dao.DataAccessor;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataAccessor dao;

    public Collection<State> getStates() {
        // TODO Auto-generated method stub
        return null;
    }

    public State getState(String stateCode) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * @see service.DataService#getDataYearSet(String)
     */
    public Collection<Integer> getDataYearSetByCode(String code) {
        // TEST DATA
        ArrayList<Integer> dataYearSet = new ArrayList<Integer>();
        dataYearSet.add(1990);
        dataYearSet.add(1991);
        return dataYearSet;
        // END TEST DATA
        // return null;
    }

    /**
     * Convert list of districts into json
     *
     * @param districts list of districts
     * @return json string
     */
    public String districtGeoDataToJson(Collection<District> districts) {
        // Turn list to string
        String geojsonStr = "{\"type\":\"FeatureCollection\",\"features\":[";
        String geojsonStrEnd = "]}";
        // guard against empty list
        boolean ran = false;
        for (District district : districts) {
            ran = true;
            geojsonStr += district.getGeoData().getBoundary() + ",";
        }
        // remove the extra comma
        if (ran) {
            geojsonStr = geojsonStr.substring(0, geojsonStr.length() - 1);
        }
        return geojsonStr + geojsonStrEnd;
    }

    /*
     * (non-Javadoc)
     * @see service.DataService#getDataByYear(String, int)
     */
    public Collection<District> getDataByYear(String selectedState, int selectedYear) {

        // TEST
        ArrayList<District> sampleDistricts = new ArrayList<District>();
        District randomDist = new District();
        randomDist.setGeoData(new GeoData());
        randomDist.getGeoData().setBoundary("{\"type\":\"Feature\",\"properties\":{\"test\":\"one\",\"test2\":\"one\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-73.32275390625,44.91035917458495],[-76.4208984375,43.41302868475145],[-78.92578124999999,43.23719944365308],[-79.73876953125,42.01665183556825],[-75.41015624999999,42.10637370579324],[-74.454345703125,41.343824581185686],[-73.597412109375,41.42625319507269],[-73.32275390625,44.91035917458495]]]}}");
        District randomDist2 = new District();
        randomDist2.setGeoData(new GeoData());
        randomDist2.getGeoData().setBoundary("{\"type\":\"Feature\",\"properties\":{\"test\":\"two\",\"test2\":\"two\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-74.454345703125,41.335575973123916],[-73.90228271484375,41.017210578228436],[-74.25384521484375,40.50126945841645],[-71.8505859375,41.07935114946899],[-73.6029052734375,41.413895564677304],[-74.454345703125,41.335575973123916]]]}}");
        sampleDistricts.add(randomDist);
        sampleDistricts.add(randomDist2);
        return sampleDistricts;
        // END TEST

        // return dao.getDataByYear(selectedState, selectedYear);
    }

    public Collection<ElectionData> getElectionDataByYear(String stateCode, int year) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getDistrictInfo(int districtId) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean upload(Object uploadTarget) {
        // TODO Auto-generated method stub
        return false;
    }

    public Collection<GeoData> getGeoDataByYear(String stateCode, int year) {
        // TODO Auto-generated method stub
        return null;
    }

}
