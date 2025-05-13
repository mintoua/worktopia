package com.logonedigital.worktopia.paie;
import com.itextpdf.text.*;
import com.logonedigital.worktopia.email.EmailService;
import com.logonedigital.worktopia.common.PdfService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class PaySlipService {


    private final PaySlipRepository paySlipRepository;
    private final PdfService pdfService;
    private final EmailService emailService;

    public PaySlipResponse generateAndSave(PaySlipRequest slipRequest) throws FileNotFoundException, DocumentException, MessagingException {

        //Filename
        String filePath = pdfService.generatePaySlipPdf(slipRequest);
        //Create and Save PaySlip
        PaySlip slip = new PaySlip();
        slip.setBaseSalary(slipRequest.getBaseSalary());
        slip.setBonus(slipRequest.getBonus());
        slip.setDeduction(slipRequest.getDeduction());
        slip.setBaseSalary(slipRequest.getBaseSalary());
        slip.setPdfLink(filePath);
        slip.setGeneratedDate(LocalDateTime.now());

        //Send Email
        //TODO : Implement email sending
        emailService.sendEmailWithPaySlip(slipRequest.getEmployee().getEmail(),filePath);
        return new PaySlipResponse(
                filePath,
                this.paySlipRepository.save(slip).getPublicId()
                );
    }
}
