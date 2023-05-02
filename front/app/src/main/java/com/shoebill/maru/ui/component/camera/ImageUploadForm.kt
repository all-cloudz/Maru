package com.shoebill.maru.ui.component.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shoebill.maru.ui.component.bottomsheet.BottomSheetIndicator
import com.shoebill.maru.ui.component.common.Chip
import com.shoebill.maru.ui.component.common.GradientButton
import com.shoebill.maru.ui.theme.MaruBackground
import com.shoebill.maru.ui.theme.MaruBrush
import com.shoebill.maru.viewmodel.CameraViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ImageUploadForm(
    bitmap: ImageBitmap,
    cameraViewModel: CameraViewModel = hiltViewModel()
) {
    val inputTag = cameraViewModel.inputTag.observeAsState("")
    val tagList = cameraViewModel.tagList.observeAsState(listOf())
    val isModalOpen = remember { mutableStateOf(false) }
    Box {
        Column(
            modifier = Modifier
                .height(400.dp)
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                BottomSheetIndicator()
                Box(modifier = Modifier.height(40.dp))
                Text(text = "나만의 스팟을 저장해 보세요!", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Box(modifier = Modifier.padding(top = 14.dp)) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = inputTag.value,
                        onValueChange = { value: String ->
                            cameraViewModel.updateInputTag(value)
                        },
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = { cameraViewModel.addTag() }
                        ),
                        shape = RoundedCornerShape(24.dp),
                        placeholder = {
                            Text(
                                text = "사진과 함께 저장하고 싶은 태그를 입력하세요!",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaruBackground,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        )
                    )
                }
                FlowRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    tagList.value.forEach { tag ->
                        Chip(text = "# $tag")
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f, false)
                    .padding(bottom = 20.dp)
            ) {
                GradientButton(
                    text = "완료",
                    gradient = MaruBrush,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onClick = {
                        isModalOpen.value = true
                    }
                )
            }
        }
        if (isModalOpen.value) {
            ConfirmModal(bitmap) {
                isModalOpen.value = false
            }
        }
    }
}