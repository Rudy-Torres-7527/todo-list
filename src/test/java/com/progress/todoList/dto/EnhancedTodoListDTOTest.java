package com.progress.todoList.dto;

import com.progress.todoList.controller.EnhancedTodoListController;
import com.progress.todoList.controller.GlobalExceptionHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.progress.todoList.constants.UrlConstants.*;
import static com.progress.todoList.constants.UrlConstants.ADD_ITEM_URI;
import static com.progress.todoList.utils.Utility.getObjectMapper;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EnhancedTodoListDTOTest {

    @InjectMocks
    private EnhancedTodoListController enhancedTodoListController;

    private MockMvc mockMvc;

    private EnhancedTodoItemDTO enhancedTodoItemDTO;

    @Before
    public void setUp() {

        enhancedTodoItemDTO = new EnhancedTodoItemDTO();
        enhancedTodoItemDTO.setName("Item_Name");
        enhancedTodoItemDTO.setDetails("Item_Details");

        mockMvc = MockMvcBuilders.standaloneSetup(enhancedTodoListController).setControllerAdvice(new GlobalExceptionHandler()).build();

    }

    @Test
    public void enhancedTodoItemDTO_nullName_validationError() throws Exception {
        enhancedTodoItemDTO.setName(null);
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void enhancedTodoItemDTO_emptyName_validationError() throws Exception {
        enhancedTodoItemDTO.setName("");
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void enhancedTodoItemDTO_blankName_validationError() throws Exception {
        enhancedTodoItemDTO.setName(" ");
        makeAPICall_returnsBadRequest("name must not be null or blank");
    }

    @Test
    public void enhancedTodoItemDTO_nullDescription_validationError() throws Exception {
        enhancedTodoItemDTO.setDetails(null);
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    @Test
    public void enhancedTodoItemDTO_emptyDescription_validationError() throws Exception {
        enhancedTodoItemDTO.setDetails("");
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    @Test
    public void enhancedTodoItemDTO_blankDescription_validationError() throws Exception {
        enhancedTodoItemDTO.setDetails(" ");
        makeAPICall_returnsBadRequest("details must not be null or blank");
    }

    private void makeAPICall_returnsBadRequest(String expectedValidation) throws Exception {
        String response = mockMvc
                .perform(MockMvcRequestBuilders.put(ENHANCED_CONTROLLER_BASE_URL + ADD_ITEM_URI, "List_Name")
                        .content(getObjectMapper().writeValueAsString(enhancedTodoItemDTO))
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn().getResponse().getContentAsString();

        assertThat(response).contains(expectedValidation);
    }
}
