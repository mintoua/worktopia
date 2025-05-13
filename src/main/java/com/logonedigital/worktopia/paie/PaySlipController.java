package com.logonedigital.worktopia.paie;

import com.itextpdf.text.DocumentException;
import com.logonedigital.worktopia.common.ApiResponse;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payslips")
public class PaySlipController {
    private final PaySlipService paySlipService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> save(
            @RequestBody PaySlipRequest request
    ) throws DocumentException, FileNotFoundException, MessagingException {
        return ResponseEntity.ok(
          new ApiResponse(
           "Generate and save successfully",
                  paySlipService.generateAndSave(request)
          )
        );
    }
}
