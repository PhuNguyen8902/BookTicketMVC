// import userService from "../../services/userService";
import { set403, setInfo, signOut } from "../slices/authSlice";
import { call, delay, put, takeLatest } from "redux-saga/effects";
import userService from "../../service/userService";
import { closeLoading, openLoading } from "../slices/pageSlice";

function* fetchInfo() {
  yield put(openLoading());
  localStorage.setItem("url", window.location.href);

  // yield delay(5000);
  const response = yield call(userService.getInfo);
  if (response.status === 403) {
    yield put(set403());
    yield put(closeLoading());
  } else {
    if (!response.message) {
      console.log(response);
      yield put(setInfo(response));
      yield put(closeLoading());
    } else {
      yield put(signOut());
      yield alert("Please login again!");
      yield localStorage.removeItem("url");
      yield put(closeLoading());
    }
  }
}

function* mySaga() {
  yield takeLatest("FETCH_INFO", fetchInfo);
}

export default mySaga;
