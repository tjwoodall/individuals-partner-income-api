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

package v1.createAmendPartnerIncome.model.request

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.*

case class OtherUntaxedUkIncome(
    shareOfOtherUntaxedUkIncome: Option[BigDecimal],
    otherUntaxedUkIncomeAdjustment: Option[BigDecimal],
    lossesBroughtForward: Option[BigDecimal],
    shareOfTaxableProfit: Option[BigDecimal],
    shareOfLoss: Option[BigDecimal],
    otherUntaxedUkIncomeLossAdjustment: Option[BigDecimal],
    carryForwardLosses: Option[BigDecimal]
)

object OtherUntaxedUkIncome {
  given Reads[OtherUntaxedUkIncome] = Json.reads[OtherUntaxedUkIncome]

  given Writes[OtherUntaxedUkIncome] = (
    (JsPath \ "shareOfOtherUntaxedUKIncome").writeNullable[BigDecimal] and
      (JsPath \ "otherUntaxedUKIncomeAdjustment").writeNullable[BigDecimal] and
      (JsPath \ "lossesBroughtForward").writeNullable[BigDecimal] and
      (JsPath \ "shareOfTaxableProfit").writeNullable[BigDecimal] and
      (JsPath \ "shareOfLoss").writeNullable[BigDecimal] and
      (JsPath \ "otherUntaxedUKIncomeLossAdjustment").writeNullable[BigDecimal] and
      (JsPath \ "carryForwardLosses").writeNullable[BigDecimal]
  )(o => Tuple.fromProductTyped(o))

}
