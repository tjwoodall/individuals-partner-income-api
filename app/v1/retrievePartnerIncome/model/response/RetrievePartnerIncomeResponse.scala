/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package v1.retrievePartnerIncome.model.response

import api.models.domain.Timestamp
import play.api.libs.functional.syntax.*
import play.api.libs.json.*
import utils.JsonUtils

case class RetrievePartnerIncomeResponse(
    submittedOn: Timestamp,
    partnershipUtr: String,
    partnershipName: String,
    startDate: Option[String],
    endDate: Option[String],
    cashBasis: Option[Boolean],
    partnershipTrades: Option[Seq[PartnershipTrade]],
    nationalInsuranceContributions: Option[NationalInsuranceContributions],
    untaxedSavingsIncome: Option[UntaxedSavingsIncome],
    ukPropertyIncome: Option[UkPropertyIncome],
    otherUntaxedUkIncome: Option[OtherUntaxedUkIncome],
    offshoreFundsIncome: Option[OffshoreFundsIncome],
    otherUntaxedForeignIncome: Option[OtherUntaxedForeignIncome],
    totalUntaxedIncomeExcludingSavings: Option[TotalUntaxedIncomeExcludingSavings],
    taxedIncomeAndDividendIncome: Option[TaxedIncomeAndDividendIncome],
    totalTaxedAndUntaxedIncome: Option[TotalTaxedAndUntaxedIncome],
    taxPaidAndDeductions: Option[TaxPaidAndDeductions]
)

object RetrievePartnerIncomeResponse extends JsonUtils {

  private val singleReads: Reads[RetrievePartnerIncomeResponse] = (
    (JsPath \ "submittedOn").read[Timestamp] and
      (JsPath \ "partnershipUTR").read[String] and
      (JsPath \ "partnershipName").read[String] and
      (JsPath \ "startDate").readNullable[String] and
      (JsPath \ "endDate").readNullable[String] and
      (JsPath \ "cashBasis").readNullable[Boolean] and
      (JsPath \ "partnershipTrades").readNullable[Seq[PartnershipTrade]].mapEmptySeqToNone and
      (JsPath \ "nationalInsuranceContributions").readNullable[NationalInsuranceContributions] and
      (JsPath \ "untaxedSavingsIncome").readNullable[UntaxedSavingsIncome] and
      (JsPath \ "ukPropertyIncome").readNullable[UkPropertyIncome] and
      (JsPath \ "otherUntaxedUKIncome").readNullable[OtherUntaxedUkIncome] and
      (JsPath \ "offshoreFundsIncome").readNullable[OffshoreFundsIncome] and
      (JsPath \ "otherUntaxedForeignIncome").readNullable[OtherUntaxedForeignIncome] and
      (JsPath \ "totalUntaxedIncomeExcludingSavings").readNullable[TotalUntaxedIncomeExcludingSavings] and
      (JsPath \ "taxedIncomeAndDividendIncome").readNullable[TaxedIncomeAndDividendIncome] and
      (JsPath \ "totalTaxedAndUntaxedIncome").readNullable[TotalTaxedAndUntaxedIncome] and
      (JsPath \ "taxPaidAndDeductions").readNullable[TaxPaidAndDeductions]
  )(RetrievePartnerIncomeResponse.apply)

  // Extract single item from the array
  implicit val reads: Reads[RetrievePartnerIncomeResponse] = Reads
    .seq(singleReads)
    .collect {
      JsonValidationError("Expected downstream JSON to be an array containing exactly one item")
    } { case Seq(single) => single }

  implicit val writes: OWrites[RetrievePartnerIncomeResponse] = Json.writes[RetrievePartnerIncomeResponse]
}
