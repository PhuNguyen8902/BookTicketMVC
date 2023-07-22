import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  popup: {
    auth: false,
  },
};

export const pageSlice = createSlice({
  name: "page",
  initialState,
  reducers: {
    openAuth: (state) => {
      state.popup.auth = true;
    },
    closeAuth: (state) => {
      state.popup.auth = false;
    },
    closePopup: (state) => {
      Object.keys(state.popup).forEach((key) => {
        state.popup[key] = false;
      });
    },
  },
});

export const { openAuth, closeAuth, closePopup } = pageSlice.actions;
export default pageSlice.reducer;
