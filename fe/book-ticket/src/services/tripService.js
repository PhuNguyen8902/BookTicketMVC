import { getData } from "../utils/fetchData"


const tripService = {
    getTrip(api){
        return getData(api);
    }
}

export default tripService;