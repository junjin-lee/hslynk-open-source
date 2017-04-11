set DB=${hiveconf:db_name};
set DB_ROLE=${hiveconf:db_role};
drop database ${hiveconf:DB} cascade;
create database IF NOT EXISTS ${hiveconf:DB};
use ${hiveconf:DB};

CREATE EXTERNAL TABLE IF NOT EXISTS client(client_id string ,dedup_client_id string ,name_data_quality string ,name_data_quality_desc string ,ssn_data_quality string ,ssn_data_quality_desc string ,dob date ,dob_data_quality string ,dob_data_quality_desc string,gender string ,gender_desc string,other_gender string ,ethnicity string ,ethnicity_desc string,race string ,race_desc string ,veteran_status string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dedup_client_id,CF:name_data_quality,CF:name_data_quality_desc,CF:ssn_data_quality,CF:ssn_data_quality_desc,CF:dob,CF:dob_data_quality,CF:dob_data_quality_desc,CF:gender,CF:gender_desc,CF:other_gender,CF:ethnicity,CF:ethnicity_desc,CF:race,CF:race_desc,CF:veteran_status) TBLPROPERTIES ("hbase.table.name" = "client_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS client_veteran_info(client_veteran_info_id string,year_entrd_service string ,year_seperated string ,world_war_2 string ,korean_war string ,vietnam_war string ,desert_storm string ,afghanistan_oef string ,iraq_oif string ,iraq_ond string ,other_theater string ,military_branch string ,military_branch_desc string ,discharge_status string ,discharge_status_desc string,date_created timestamp ,date_updated timestamp ,user_id string ,client_id string ,export_id string,deleted string,project_group_code string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:year_entrd_service,CF:year_seperated,CF:world_war_2,CF:korean_war,CF:vietnam_war,CF:desert_storm,CF:afghanistan_oef,CF:iraq_oif,CF:iraq_ond,CF:other_theater,CF:military_branch,CF:military_branch_desc,CF:discharge_status,CF:discharge_status_desc,CF:date_created,CF:date_updated,CF:user_id,CF:client_id,CF:export_id,CF:deleted, CF:project_group_code,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "client_veteran_info_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS contact(contact_id string,contact_date timestamp ,contact_location string ,contact_location_desc string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:contact_date,CF:contact_location,CF:contact_location_desc,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "contact_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS dateofengagement(dateofengagement_id string,dateofengagement timestamp ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateofengagement,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "dateofengagement_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS disabilities(disabilities_id string,disabilityresponse string ,disabilityresponse_desc string ,disabilitytype string ,disabilitytype_desc string,documentationonfile string ,documentationonfile_desc string ,indefiniteandimpairs string ,indefiniteandimpairs_desc string ,pathhowconfirmed string ,pathhowconfirmed_desc string ,pathsmiinformation string ,pathsmiinformation_desc string ,project_entry_id string ,receivingservices string ,receivingservices_desc string ,tcellcountavailable string ,tcellcountavailable_desc string ,tcellcount string ,tcellcount_desc string ,tcellcountsource string ,tcellcountsource_desc string ,viral_load_available string ,viral_load_available_desc string ,viral_load string ,viral_load_desc string ,viral_load_source string ,viral_load_source_desc string ,information_date timestamp,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:disabilityresponse,CF:disabilityresponse_desc,CF:disabilitytype,CF:disabilitytype_desc,CF:documentationonfile,CF:documentationonfile_desc,CF:indefiniteandimpairs,CF:indefiniteandimpairs_desc,CF:pathhowconfirmed,CF:pathhowconfirmed_desc,CF:pathsmiinformation,CF:pathsmiinformation_desc,CF:enrollmentid,CF:receivingservices,CF:receivingservices_desc,CF:tcellcountavailable,CF:tcellcountavailable_desc,CF:tcellcount,CF:tcellcount_desc,CF:tcellcountsource,CF:tcellcountsource_desc,CF:viral_load_available,CF:viral_load_available_desc,CF:viral_load,CF:viral_load_desc,CF:viral_load_source,CF:viral_load_source_desc,CF:information_date,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "disabilities_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS domesticviolence(domesticviolence_id string ,domesticviolencevictim string ,domesticviolencevictim_desc string ,project_entry_id string ,whenoccurred string ,whenoccurred_desc string ,currently_fleeing string ,currently_fleeing_desc string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:domesticviolencevictim,CF:domesticviolencevictim_desc,CF:enrollmentid,CF:whenoccurred,CF:whenoccurred_desc,CF:currently_fleeing,CF:currently_fleeing_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "domesticviolence_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS education(education_id string ,lastgradecompleted string ,lastgradecompleted_desc string ,school_status string ,school_status_desc string ,project_entry_id string ,project_group_code string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:lastgradecompleted,CF:lastgradecompleted_desc,CF:school_status,CF:school_status_desc,CF:enrollmentid,CF:project_group_code,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "education_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS employment(employment_id string ,information_date timestamp ,employed string ,employment_type string ,employment_type_desc string,not_employed_reason string ,not_employed_reason_desc string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:information_date,CF:employed,CF:employment_type,CF:employment_type_desc,CF:not_employed_reason,CF:not_employed_reason_desc,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "employment_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment(project_entry_id string ,entrydate timestamp ,householdid string ,relationshiptohoh string ,relationshiptohoh_desc string ,otherresidenceprior string ,residenceprior string ,residenceprior_desc string ,residencepriorlengthofstay string ,residencepriorlengthofstay_desc string ,disablingcondition string ,disablingcondition_desc string ,housingstatus string ,housingstatus_desc string ,entryfromstreetessh string ,datetostreetessh string ,timeshomelesspastthreeyears string ,timeshomelesspastthreeyears_desc string ,monthshomelesspastthreeyears string ,monthshomelesspastthreeyears_desc string ,ProjectID string,ageatentry int,chronichomeless string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:entrydate,CF:householdid,CF:relationshiptohoh,CF:relationshiptohoh_desc,CF:otherresidenceprior,CF:residenceprior,CF:residenceprior_desc,CF:residencepriorlengthofstay,CF:residencepriorlengthofstay_desc,CF:disablingcondition,CF:disablingcondition_desc,CF:housingstatus,CF:housingstatus_desc,CF:entryfromstreetessh,CF:datetostreetessh,CF:timeshomelesspastthreeyears,CF:timeshomelesspastthreeyears_desc,CF:monthshomelesspastthreeyears,CF:monthshomelesspastthreeyears_desc,CF:client_id,CF:ageatentry,CF:chronichomeless,CF:source_system_id,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "enrollment_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment(project_entry_id string ,entrydate timestamp ,householdid string ,relationshiptohoh string ,relationshiptohoh_desc string ,otherresidenceprior string ,residenceprior string ,residenceprior_desc string ,residencepriorlengthofstay string ,residencepriorlengthofstay_desc string ,disablingcondition string ,disablingcondition_desc string ,housingstatus string ,housingstatus_desc string ,entryfromstreetessh string ,datetostreetessh string ,timeshomelesspastthreeyears string ,timeshomelesspastthreeyears_desc string ,monthshomelesspastthreeyears string ,monthshomelesspastthreeyears_desc string ,ProjectID string,ageatentry int,chronichomeless string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:entrydate,CF:householdid,CF:relationshiptohoh,CF:relationshiptohoh_desc,CF:otherresidenceprior,CF:residenceprior,CF:residenceprior_desc,CF:residencepriorlengthofstay,CF:residencepriorlengthofstay_desc,CF:disablingcondition,CF:disablingcondition_desc,CF:housingstatus,CF:housingstatus_desc,CF:entryfromstreetessh,CF:datetostreetessh,CF:timeshomelesspastthreeyears,CF:timeshomelesspastthreeyears_desc,CF:monthshomelesspastthreeyears,CF:monthshomelesspastthreeyears_desc,CF:client_id,CF:ageatentry,CF:chronichomeless,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "enrollment_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment_coc(enrollment_coc_id string ,client_code string ,project_entry_id string ,information_date timestamp ,project_group_code string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:client_code,CF:enrollmentid,CF:information_date,CF:project_group_code,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "enrollment_coc_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS entryrhsp(entryrhsp_id string ,worst_housing_situation string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:worst_housing_situation,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "entryrhsp_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS entryrhy(entryrhy_id string,project_entry_id string,sexual_orientation string,sexual_orientation_desc string,formerly_ward_child_welfr_forest_care string,formerly_ward_child_welfr_forest_care_desc string,  years_child_welfr_forest_care string,years_child_welfr_forest_care_desc string,  months_child_welfr_forest_care string,months_child_welfr_forest_care_desc string,  formerly_ward_of_juvenile_justice string,formerly_ward_of_juvenile_justice_desc string,years_juvenile_justice string,years_juvenile_justice_desc string,  house_hold_dynamics string,house_hold_dynamics_desc string,  sexual_orientatiion_gender_identity_youth string,sexual_orientatiion_gender_identity_youth_desc string, sexual_orientatiion_gender_identity_family_mbr string,sexual_orientatiion_gender_identity_family_mbr_desc string,  housing_issues_youth string,housing_issues_youth_desc string,  housing_issues_family_mbr string,housing_issues_family_mbr_desc string,school_education_issues_youth string,school_education_issues_youth_desc string,school_education_issues_family_mbr string,school_education_issues_family_mbr_desc string, unemployement_youth string,unemployement_youth_desc string,unemployement_family_mbr string,unemployement_family_mbr_desc string,  mental_health_issues_youth string,mental_health_issues_youth_desc string,mental_health_issues_family_mbr string,mental_health_issues_family_mbr_desc string,health_issues_youth string,health_issues_youth_desc string,health_issues_family_mbr string,health_issues_family_mbr_desc string,  physical_disability_youth string,physical_disability_youth_desc string,physical_disability_family_mbr string,physical_disability_family_mbr_desc string,mental_disability_youth string,mental_disability_youth_desc string,mental_disability_family_mbr string,mental_disability_family_mbr_desc string,abuse_and_neglect_youth string,abuse_and_neglect_youth_desc string,abuse_and_neglect_family_mbr string,abuse_and_neglect_family_mbr_desc string,alcohol_drug_abuse_youth string,alcohol_drug_abuse_youth_desc string,alcohol_drug_abuse_family_mbr string,alcohol_drug_abuse_family_mbr_desc string,insufficient_income_to_support_youth string,insufficient_income_to_support_youth_desc string,active_military_parent string,active_military_parent_desc string,incarcerated_parent string,incarcerated_parent_desc string,incarcerated_parent_status string,incarcerated_parent_status_desc string,referral_source string,referral_source_desc string,count_out_reach_referral_approaches string,count_out_reach_referral_approaches_desc string,exchange_for_sex string,exchange_for_sex_desc string,exchange_for_sex_past_three_months string,exchange_for_sex_past_three_months_desc string,count_of_exchange_for_sex string,count_of_exchange_for_sex_desc string,asked_of_forced_to_exchange_for_sex string,asked_of_forced_to_exchange_for_sex_desc string,asked_of_forced_to_exchange_for_sex_past_three_months string,asked_of_forced_to_exchange_for_sex_past_three_months_desc string,work_place_violence_threat string,work_place_violence_threat_desc string,work_place_promise_difference string,work_place_promise_difference_desc string,coerced_to_continue_work string,coerced_to_continue_work_desc string,labor_exploit_past_three_months string,labor_exploit_past_three_months_desc string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:enrollmentid,CF:sexual_orientation,CF:sexual_orientation_desc,CF:formerly_ward_child_welfr_forest_care,CF:formerly_ward_child_welfr_forest_care_desc,CF:years_child_welfr_forest_care,CF:years_child_welfr_forest_care_desc,CF:months_child_welfr_forest_care,CF:months_child_welfr_forest_care_desc,CF:formerly_ward_of_juvenile_justice,CF:formerly_ward_of_juvenile_justice_desc,CF:years_juvenile_justice,CF:years_juvenile_justice_desc,CF:house_hold_dynamics,CF:house_hold_dynamics_desc,CF:sexual_orientatiion_gender_identity_youth,CF:sexual_orientatiion_gender_identity_youth_desc,CF:sexual_orientatiion_gender_identity_family_mbr,CF:sexual_orientatiion_gender_identity_family_mbr_desc,CF:housing_issues_youth,CF:housing_issues_youth_desc,CF:housing_issues_family_mbr,CF:housing_issues_family_mbr_desc,CF:school_education_issues_youth,CF:school_education_issues_youth_desc,CF:school_education_issues_family_mbr,CF:school_education_issues_family_mbr_desc,CF:unemployement_youth,CF:unemployement_youth_desc,CF:unemployement_family_mbr,CF:unemployement_family_mbr_desc,CF:mental_health_issues_youth,CF:mental_health_issues_youth_desc,CF:mental_health_issues_family_mbr,CF:mental_health_issues_family_mbr_desc,CF:health_issues_youth,CF:health_issues_youth_desc,CF:health_issues_family_mbr,CF:health_issues_family_mbr_desc,CF:physical_disability_youth,CF:physical_disability_youth_desc,CF:physical_disability_family_mbr,CF:physical_disability_family_mbr_desc,CF:mental_disability_youth,CF:mental_disability_youth_desc,CF:mental_disability_family_mbr,CF:mental_disability_family_mbr_desc,CF:abuse_and_neglect_youth,CF:abuse_and_neglect_youth_desc,CF:abuse_and_neglect_family_mbr,CF:abuse_and_neglect_family_mbr_desc,CF:alcohol_drug_abuse_youth,CF:alcohol_drug_abuse_youth_desc,CF:alcohol_drug_abuse_family_mbr,CF:alcohol_drug_abuse_family_mbr_desc,CF:insufficient_income_to_support_youth,CF:insufficient_income_to_support_youth_desc,CF:active_military_parent,CF:active_military_parent_desc,CF:incarcerated_parent,CF:incarcerated_parent_desc,CF:incarcerated_parent_status,CF:incarcerated_parent_status_desc,CF:referral_source,CF:referral_source_desc,CF:count_out_reach_referral_approaches,CF:count_out_reach_referral_approaches_desc,CF:exchange_for_sex,CF:exchange_for_sex_desc,CF:exchange_for_sex_past_three_months,CF:exchange_for_sex_past_three_months_desc,CF:count_of_exchange_for_sex,CF:count_of_exchange_for_sex_desc,CF:asked_of_forced_to_exchange_for_sex,CF:asked_of_forced_to_exchange_for_sex_desc,CF:asked_of_forced_to_exchange_for_sex_past_three_months,CF:asked_of_forced_to_exchange_for_sex_past_three_months_desc,CF:work_place_violence_threat,CF:work_place_violence_threat_desc,CF:work_place_promise_difference,CF:work_place_promise_difference_desc,CF:coerced_to_continue_work,CF:coerced_to_continue_work_desc,CF:labor_exploit_past_three_months,CF:labor_exploit_past_three_months_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "entryrhy_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS exit(exit_id string,destination string ,destination_desc string,exitdate timestamp  ,otherdestination string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:destination,CF:destination_desc,CF:exitdate,CF:otherdestination,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "exit_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS exitpath(exitpath_id string,connection_with_soar string ,connection_with_soar_desc string ,project_group_code string,exitid string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:connection_with_soar,CF:connection_with_soar_desc,CF:project_group_code,CF:exitid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "exitpath_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS exithousingassessment(exithousingassessment_id string ,exitid string ,housingassessment string ,housingassessment_desc string ,subsidyinformation string ,subsidyinformation_desc string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:exitid,CF:housingassessment,CF:housingassessment_desc,CF:subsidyinformation,CF:subsidyinformation_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "exithousingassessment_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS exitplansactions(exitplansactions_id string,assistancemainstreambenefits string ,exitcounseling string ,exitid string  ,furtherfollowupservices string ,otheraftercareplanoraction string ,permanenthousingplacement string ,resourcepackage string ,scheduledfollowupcontacts string ,temporaryshelterplacement string ,writtenaftercareplan string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assistancemainstreambenefits,CF:exitcounseling,CF:exitid,CF:furtherfollowupservices,CF:otheraftercareplanoraction,CF:permanenthousingplacement,CF:resourcepackage,CF:scheduledfollowupcontacts,CF:temporaryshelterplacement,CF:writtenaftercareplan,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "exitplansactions_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS exitrhy(exitrhy_id string ,exitid string ,project_completion_status string ,early_exit_reason string ,early_exit_reason_desc string ,family_reunification_achieved string ,family_reunification_achieved_desc string ,written_after_care_plan string ,written_after_care_plan_desc string ,assistance_main_stream_benefits string ,assistance_main_stream_benefits_desc string ,permenant_housing_placement string ,permenant_housing_placement_desc string ,temp_shelter_placement string ,temp_shelter_placement_desc string ,exit_counseling string ,exit_counseling_desc string ,further_followup_services string ,further_followup_services_desc string ,scheduled_followup_contacts string ,scheduled_followup_contacts_desc string ,resource_package string ,resource_package_desc string ,other_aftercare_plan_or_action string ,other_aftercare_plan_or_action_desc string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:exitid,CF:project_completion_status,CF:early_exit_reason,CF:early_exit_reason_desc,CF:family_reunification_achieved,CF:family_reunification_achieved_desc,CF:written_after_care_plan,CF:written_after_care_plan_desc,CF:assistance_main_stream_benefits,CF:assistance_main_stream_benefits_desc,CF:permenant_housing_placement,CF:permenant_housing_placement_desc,CF:temp_shelter_placement,CF:temp_shelter_placement_desc,CF:exit_counseling,CF:exit_counseling_desc,CF:further_followup_services,CF:further_followup_services_desc,CF:scheduled_followup_contacts,CF:scheduled_followup_contacts_desc,CF:resource_package,CF:resource_package_desc,CF:other_aftercare_plan_or_action,CF:other_aftercare_plan_or_action_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "exitrhy_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS funder(funder_id string ,enddate timestamp ,funder string ,funder_desc string,grantid string ,projectid string ,startdate timestamp  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:enddate,CF:funder,CF:funder_desc,CF:grantid,CF:projectid,CF:startdate,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "funder_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS health_status(health_status_id string ,project_entry_id string ,information_date timestamp ,health_category string ,health_category_desc string,health_status string ,health_status_desc string,due_date timestamp  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:enrollmentid,CF:information_date,CF:health_category,CF:health_category_desc,CF:health_status,CF:health_status_desc,CF:due_date,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "health_status_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS healthinsurance(healthinsurance_id string,cobra string ,employerprovided string  ,insurancefromanysource string ,medicaid string ,medicare string ,nocobrareason string ,nocobrareason_desc string ,noemployerprovidedreason string ,noemployerprovidedreason_desc string ,nomedicaidreason string ,nomedicaidreason_desc string ,nomedicarereason string ,nomedicarereason_desc string ,noprivatepayreason string ,noprivatepayreason_desc string ,noschipreason string ,noschipreason_desc string ,nostatehealthinsreason string ,nostatehealthinsreason_desc string ,novamedreason string ,novamedreason_desc string ,privatepay string ,project_entry_id string ,schip string ,statehealthins string ,vamedicalservices string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:cobra,CF:employerprovided,CF:insurancefromanysource,CF:medicaid,CF:medicare,CF:nocobrareason,CF:nocobrareason_desc,CF:noemployerprovidedreason,CF:noemployerprovidedreason_desc,CF:nomedicaidreason,CF:nomedicaidreason_desc,CF:nomedicarereason,CF:nomedicarereason_desc,CF:noprivatepayreason,CF:noprivatepayreason_desc,CF:noschipreason,CF:noschipreason_desc,CF:nostatehealthinsreason,CF:nostatehealthinsreason_desc,CF:novamedreason,CF:novamedreason_desc,CF:privatepay,CF:enrollmentid,CF:schip,CF:statehealthins,CF:vamedicalservices,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "healthinsurance_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS housingassessmentdisposition(housingassessmentdisposition_id string,assessmentdisposition string ,assessmentdisposition_desc string,exitid string  ,otherdisposition string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assessmentdisposition,CF:assessmentdisposition_desc,CF:exitid,CF:otherdisposition,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "housingassessmentdisposition_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS incomeandsources(incomeandsources_id string,alimony string , alimony_desc string, alimonyamount string ,childsupport string , childsupport_desc string, childsupportamount string ,earned string , earned_desc string, earnedamount string ,ga string ,ga_desc string, gaamount string  ,incomefromanysource string , incomefromanysource_desc string, othersource string , othersource_desc string, othersourceamount string ,othersourceidentify string ,pension string ,pension_desc string, pensionamount string ,privatedisability string , privatedisability_desc string, privatedisabilityamount string ,project_entry_id string ,socsecretirement string ,socsecretirement_desc string, socsecretirementamount string ,ssdi string ,ssdi_desc string, ssdiamount string ,ssi string ,ssi_desc string, ssiamount string ,tanf string ,tanf_desc string, tanfamount string ,totalmonthlyincome string ,unemployment string , unemployment_desc string, unemploymentamount string ,vadisabilitynonservice string ,vadisabilitynonservice_desc string, vadisabilitynonserviceamount string ,vadisabilityservice string ,vadisabilityservice_desc string, vadisabilityserviceamount string ,workerscomp string , workerscomp_desc string, workerscompamount string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:alimony,CF:alimony_desc,CF:alimonyamount,CF:childsupport,CF:childsupport_desc,CF:childsupportamount,CF:earned,CF:earned_desc,CF:earnedamount,CF:ga,CF:ga_desc,CF:gaamount,CF:incomefromanysource,CF:incomefromanysource_desc,CF:othersource,CF:othersource_desc,CF:othersourceamount,CF:othersourceidentify,CF:pension,CF:pension_desc,CF:pensionamount,CF:privatedisability,CF:privatedisability_desc,CF:privatedisabilityamount,CF:enrollmentid,CF:socsecretirement,CF:socsecretirement_desc,CF:socsecretirementamount,CF:ssdi,CF:ssdi_desc,CF:ssdiamount,CF:ssi,CF:ssi_desc,CF:ssiamount,CF:tanf,CF:tanf_desc,CF:tanfamount,CF:totalmonthlyincome,CF:unemployment,CF:unemployment_desc,CF:unemploymentamount,CF:vadisabilitynonservice,CF:vadisabilitynonservice_desc,CF:vadisabilitynonserviceamount,CF:vadisabilityservice,CF:vadisabilityservice_desc,CF:vadisabilityserviceamount,CF:workerscomp,CF:workerscomp_desc,CF:workerscompamount,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "incomeandsources_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS inventory(inventory_id string ,availabilty string ,bedinventory string ,bedtype string ,bedtype_desc string ,hmisparticipatingbeds string ,householdtype string ,householdtype_desc string,inventoryenddate timestamp ,inventorystartdate timestamp ,project_coc_id string ,unitinventory string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:availabilty,CF:bedinventory,CF:bedtype,CF:bedtype_desc,CF:hmisparticipatingbeds,CF:householdtype,CF:householdtype_desc,CF:inventoryenddate,CF:inventorystartdate,CF:project_coc_id,CF:unitinventory,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "inventory_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS medicalassistance(medicalassistance_id string ,hivaidsassistance string ,hivaidsassistance_desc string ,nohivaidsassistancereason string ,nohivaidsassistancereason_desc string ,adap string ,adap_desc string ,noadapreason string ,noadapreason_desc string ,information_date timestamp ,project_entry_id string ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:hivaidsassistance,CF:hivaidsassistance_desc,CF:nohivaidsassistancereason,CF:nohivaidsassistancereason_desc,CF:adap,CF:adap_desc,CF:noadapreason,CF:noadapreason_desc,CF:information_date,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "medicalassistance_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS noncashbenefits(noncashbenefits_id string,benefitsfromanysource string ,benefitsfromanysource_desc string  ,othersource string ,othersource_desc string ,othersourceidentify string ,othertanf string ,othertanf_desc string ,project_entry_id string ,rentalassistanceongoing string ,rentalassistanceongoing_desc string ,rentalassistancetemp string ,rentalassistancetemp_desc string ,snap string ,snap_desc string ,tanfchildcare string ,tanfchildcare_desc string ,tanftransportation string ,tanftransportation_desc string ,wic string ,wic_desc string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:benefitsfromanysource,CF:benefitsfromanysource_desc,CF:othersource,CF:othersource_desc,CF:othersourceidentify,CF:othertanf,CF:othertanf_desc,CF:enrollmentid,CF:rentalassistanceongoing,CF:rentalassistanceongoing_desc,CF:rentalassistancetemp,CF:rentalassistancetemp_desc,CF:snap,CF:snap_desc,CF:tanfchildcare,CF:tanfchildcare_desc,CF:tanftransportation,CF:tanftransportation_desc,CF:wic,CF:wic_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "noncashbenefits_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS organization(organization_id string,organizationcommonname string  ,organizationname string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:organizationcommonname,CF:organizationname,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "organization_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS path_status(path_status_id string ,date_of_status timestamp ,client_enrolled_in_path string ,reason_not_enrolled string ,reason_not_enrolled_desc string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:date_of_status,CF:client_enrolled_in_path,CF:reason_not_enrolled,CF:reason_not_enrolled_desc,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "path_status_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS project(project_id string ,continuumproject string ,organizationid string ,projectcommonname string ,projectname string ,projecttype string ,projecttype_desc string ,residentialaffiliation string ,targetpopulation string ,targetpopulation_desc string ,trackingmethod string ,trackingmethod_desc string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:continuumproject,CF:organizationid,CF:projectcommonname,CF:projectname,CF:projecttype,CF:projecttype_desc,CF:residentialaffiliation,CF:targetpopulation,CF:targetpopulation_desc,CF:trackingmethod,CF:trackingmethod_desc,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "project_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS coc(coc_id string ,coccode string ,projectid string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:coccode,CF:projectid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "coc_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS residentialmoveindate(residentialmoveindate_id string,inpermanenthousing string ,inpermanenthousing_desc string ,project_entry_id string ,residentialmoveindate timestamp   ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:inpermanenthousing,CF:inpermanenthousing_desc,CF:enrollmentid,CF:residentialmoveindate,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "residentialmoveindate_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS rhybcp_status(rhybcp_status_id string ,status_date timestamp ,fysb_youth string ,fysb_youth_desc string ,reason_no_services string ,reason_no_services_desc string,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:status_date,CF:fysb_youth,CF:fysb_youth_desc,CF:reason_no_services,CF:reason_no_services_desc,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "rhybcp_status_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS service_fa_referral(service_fa_referral_id string,dateprovided timestamp ,service_category string ,funder_list string ,type_provided string ,other_type_provided string ,sub_type_provided string ,fa_amount string ,referral_outcome string ,referraloutcome_desc string ,project_entry_id string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateprovided,CF:service_category,CF:funder_list,CF:type_provided,CF:other_type_provided,CF:sub_type_provided,CF:fa_amount,CF:referral_outcome,CF:referraloutcome_desc,CF:enrollmentid,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "service_fa_referral_${hiveconf:DB}");
CREATE EXTERNAL TABLE IF NOT EXISTS site(site_id string ,address string ,city string ,geocode string ,principal_site string ,project_coc_id string ,state string ,zip string  ,source_system_id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:address,CF:city,CF:geocode,CF:principal_site,CF:project_coc_id,CF:state,CF:zip,CF:source_system_id") TBLPROPERTIES ("hbase.table.name" = "site_${hiveconf:DB}");


--CREATE INDEX client_index_project_grp ON TABLE client(project_group_code)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;
--
--CREATE INDEX client_index_export_id ON TABLE client(export_id)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;
--
--CREATE INDEX enrollment_index_export_id ON TABLE enrollment(export_id)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;
--
--CREATE INDEX enrollment_index_project_grp ON TABLE enrollment(project_group_code)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;

drop role ${hiveconf:DB_ROLE};
create role ${hiveconf:DB_ROLE};
grant select on database ${hiveconf:DB} to role ${hiveconf:DB_ROLE};