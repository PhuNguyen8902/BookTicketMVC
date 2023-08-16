import { getData } from "../utils/fetchData"


const stationService = {
    getStation(api){
        return getData(api);
    }
}

export default stationService;