import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  popup: {
    isLoading: false,
  },
};

export const pageSlice = createSlice({
  name: "page",
  initialState,
  reducers: {
    openLoading: (state) => {
      state.popup.isLoading = true;
    },
    closeLoading: (state) => {
      state.popup.isLoading = false;
    },
    closePopup: (state) => {
      Object.keys(state.popup).forEach((key) => {
        state.popup[key] = false;
      });
    },
  },
});

export const { openLoading, closeLoading, closePopup } = pageSlice.actions;
export default pageSlice.reducer;
