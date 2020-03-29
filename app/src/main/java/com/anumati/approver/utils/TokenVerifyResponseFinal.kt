import androidx.annotation.Keep
import com.anumati.approver.models.ApplicationStatus
import com.anumati.approver.models.ApplicationType
import com.anumati.approver.models.ProofType
import java.io.Serializable

@Keep
data class TokenVerifyResponseFinal(
    val applicationID: Int,
    val applicationStatus: ApplicationStatus,
    val applicationType: ApplicationType,
    val createdAt: Long,
    val endTime: String?,
    val entity: Entity?,
//    val fromPlace: Place?,
    val issuerId: Int,
    val purpose: String,
    val startTime: String?,
//    val toPlace: Place?,
    val token: String
):Serializable {

    @Keep
    data class Entity(
        val dob: String?,
        val firstName: String?,
        val lastName: String?,
        val phoneNumber: String?,
        val proofId: String?,
        val registrationNumber: String?,
        val vehicleModel: String?,
        val proofType: ProofType?
    ):Serializable
}