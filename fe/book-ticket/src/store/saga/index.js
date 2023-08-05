import authService from "../../services/authService";
import userService from "../../services/userService";
import { setInfo, signOut } from "../slices/authSlice";
import { call, put, takeLatest } from "redux-saga/effects";

function* fetchInfo() {
  const response = yield call(userService.getInfo);
  if (!response.message) {
    yield put(setInfo(response));
    yield console.log(response);
  } else {
    yield put(signOut());
    yield console.log(response);
    yield alert(response.message);
  }
}

function* mySaga() {
  yield takeLatest("FETCH_INFO", fetchInfo);
}

export default mySaga;
