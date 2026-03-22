export const buildRequiredRules = fields => {
  return fields.reduce((rules, field) => {
    if (field.required === false) {
      return rules
    }

    rules[field.prop] = [
      { required: true, message: field.type === 'select' || field.type === 'date' ? `请选择${field.label}` : `请输入${field.label}`, trigger: field.type === 'select' ? 'change' : 'blur' }
    ]
    return rules
  }, {})
}
