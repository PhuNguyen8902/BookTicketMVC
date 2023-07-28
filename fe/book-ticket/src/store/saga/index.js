import authService from "../../services/authService";
import pictureService from "../../services/pictureService";
import userService from "../../services/userService";
import { setInfo, signOut } from "../slices/authSlice";
import { call, put, takeLatest } from "redux-saga/effects";

function* fetchInfo() {
  const response = yield call(userService.getInfo);
  if (!response.message) {
    yield put(setInfo(response));
  } else {
    yield put(signOut());
    console.log(response);
    alert(response.message);
  }
}
function* sendPic() {
  const response = yield call(pictureService.postPicture);
  if (!response.message) {
    // yield put(setInfo(response));
    console.log(response);
  } else {
    yield put(signOut());
    console.log(response);
    alert(response.message);
  }
}

function* mySaga() {
  yield takeLatest("FETCH_INFO", fetchInfo);
  yield takeLatest("SEND_PIC", sendPic);
}

export default mySaga;
