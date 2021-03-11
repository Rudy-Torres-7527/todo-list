package com.progress.todoList.dto;

import com.progress.todoList.controller.GlobalExceptionHandler;
import com.progress.todoList.controller.TodoListController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.progress.todoList.constants.UrlConstants.*;
import static com.progress.todoList.utils.Utility.getObjectMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TodoListDTOTest {

    @InjectMocks
    private TodoListController todoListController;

    private MockMvc mockMvc;

    private TodoItemDTO todoItemDTO;

    @Before
    public void setUp() {

        todoItemDTO = new TodoItemDTO();
        todoItemDTO.setName("Item_Name");
        todoItemDTO.setDetails("Item_Details");

        mockMvc = MockMvcBuilders.standaloneSetup(todoListController).setControllerAdvice(new GlobalExceptionHandler()).build();

    }

    @Test
    public void todoItemDTO_nullName_validationError() throws Exception {
        todoItemDTO.setName(null);
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void todoItemDTO_emptyName_validationError() throws Exception {
        todoItemDTO.setName("");
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void todoItemDTO_blankName_validationError() throws Exception {
        todoItemDTO.setName(" ");
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void todoItemDTO_nullDetails_validationError() throws Exception {
        todoItemDTO.setDetails(null);
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    @Test
    public void todoItemDTO_emptyDetails_validationError() throws Exception {
        todoItemDTO.setDetails("");
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    @Test
    public void todoItemDTO_blankDetails_validationError() throws Exception {
        todoItemDTO.setDetails(" ");
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    private void makeAPICall_returnsBadRequest(String expectedValidation) throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.put(BASIC_CONTROLLER_BASE_URL + ADD_ITEM_URI, "List_Name")
                        .content(getObjectMapper().writeValueAsString(todoItemDTO))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        assertThat(response).contains(expectedValidation);
    }
}
