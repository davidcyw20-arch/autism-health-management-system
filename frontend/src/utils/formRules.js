export const buildRequiredRules = fields => {
  return fields.reduce((rules, field) => {
    rules[field.prop] = [
      { required: true, message: `请输入${field.label}`, trigger: 'blur' }
    ]
    return rules
  }, {})
}
